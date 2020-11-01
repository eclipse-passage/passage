/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.internal.licenses.core;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.floating.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.floating.model.api.FeatureGrant;
import org.eclipse.passage.lic.floating.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.floating.model.api.FloatingServer;
import org.eclipse.passage.lic.floating.model.api.LicenseRequisites;
import org.eclipse.passage.lic.floating.model.api.ProductRef;
import org.eclipse.passage.lic.floating.model.api.UserGrant;
import org.eclipse.passage.lic.floating.model.api.ValidityPeriod;
import org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.floating.model.api.VersionMatch;
import org.eclipse.passage.lic.floating.model.meta.FloatingFactory;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Disk;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.internal.api.FloatingLicenseRequest;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.eclipse.passage.loc.internal.users.UserRegistry;

final class FloatingLicensePackFromRequest implements Supplier<FloatingLicensePack> {

	private final FloatingLicenseRequest request;
	private final LicenseRegistry licenses;
	private final UserRegistry users;
	private final Optional<FloatingLicensePack> template;

	FloatingLicensePackFromRequest(FloatingLicenseRequest request, Optional<FloatingLicensePack> template,
			LicenseRegistry licenses, UserRegistry users) {
		this.request = request;
		this.template = template;
		this.licenses = licenses;
		this.users = users;
	}

	@Override
	public FloatingLicensePack get() {
		FloatingLicensePack pack = FloatingFactory.eINSTANCE.createFloatingLicensePack();
		pack.setLicense(license());
		pack.setHost(floatingServer());
		userGrants().forEach(pack.getUsers()::add);
		featureGrants(pack).forEach(pack.getFeatures()::add);
		return pack;
	}

	private LicenseRequisites license() {
		LicenseRequisites license = FloatingFactory.eINSTANCE.createLicenseRequisites();
		license.setCompany(company());
		license.setIdentifier(request.identifier());
		license.setIssueDate(new Date());
		license.setPlan(request.plan());
		license.setProduct(product());
		license.setValid(period());
		return license;
	}

	private FloatingServer floatingServer() {
		FloatingServer server = FloatingFactory.eINSTANCE.createFloatingServer();
		server.setIdentifier(serverId());
		server.setAuthentication(serverAuthentication());
		return server;
	}

	private String serverId() {
		return template//
				.map(l -> l.getHost().getIdentifier())//
				.orElse("Floating Server A"); //$NON-NLS-1$
	}

	private EvaluationInstructions serverAuthentication() {
		EvaluationInstructions auth = FloatingFactory.eINSTANCE.createEvaluationInstructions();
		auth.setType(serverAuthenticationType());
		auth.setExpression(serverAuthenticationExpression());
		return auth;
	}

	private String serverAuthenticationType() {
		return template//
				.map(l -> l.getHost().getAuthentication().getType())//
				.orElseGet(this::defaultEvaluationType);
	}

	private String serverAuthenticationExpression() {
		return template//
				.map(l -> l.getHost().getAuthentication().getExpression())//
				.orElse(String.format("%s=%s", new Disk.Serial().toString(), "?")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private String company() {
		return users.getUser(request.users().iterator().next()).getUserOrigin().getIdentifier();
	}

	private ValidityPeriod period() {
		ValidityPeriodClosed period = FloatingFactory.eINSTANCE.createValidityPeriodClosed();
		period.setFrom(request.validFrom());
		period.setUntil(request.validUntil());
		return period;
	}

	private ProductRef product() {
		ProductRef product = FloatingFactory.eINSTANCE.createProductRef();
		product.setProduct(request.productIdentifier());
		product.setVersion(request.productVersion());
		return product;
	}

	private Collection<UserGrant> userGrants() {
		return request.users().stream()//
				.map(users::getUser)//
				.map(this::userGrant)//
				.collect(Collectors.toSet());
	}

	private UserGrant userGrant(UserDescriptor user) {
		UserGrant grant = FloatingFactory.eINSTANCE.createUserGrant();
		grant.setAuthentication(userAuthentication(user));
		grant.setUser(user.getIdentifier());
		return grant;
	}

	private EvaluationInstructions userAuthentication(UserDescriptor user) {
		EvaluationInstructions auth = FloatingFactory.eINSTANCE.createEvaluationInstructions();
		auth.setExpression(userAuthenticationExpression(user));
		auth.setType(userAuthenticationType(user));
		return auth;
	}

	private String userAuthenticationType(UserDescriptor user) {
		return template//
				.flatMap(l -> forUser(l.getUsers(), user))//
				.map(UserGrant::getAuthentication)//
				.map(EvaluationInstructions::getType)//
				.orElseGet(user::getPreferredConditionType);
	}

	private String userAuthenticationExpression(UserDescriptor user) {
		return template//
				.flatMap(l -> forUser(l.getUsers(), user))//
				.map(UserGrant::getAuthentication)//
				.map(EvaluationInstructions::getExpression)//
				.orElseGet(user::getPreferredConditionExpression);
	}

	private Optional<UserGrant> forUser(List<UserGrant> all, UserDescriptor user) {
		return all.stream().filter(u -> user.getEmail().equals(u.getUser())).findFirst();
	}

	private Collection<FeatureGrant> featureGrants(FloatingLicensePack pack) {
		LicensePlanDescriptor plan = licenses.getLicensePlan(request.plan());
		AtomicInteger counter = new AtomicInteger(0);
		return StreamSupport.stream(plan.getLicensePlanFeatures().spliterator(), false)//
				.map(feature -> featureGrant(feature, pack, counter.getAndIncrement())) //
				.collect(Collectors.toSet());
	}

	private FeatureGrant featureGrant(LicensePlanFeatureDescriptor feature, FloatingLicensePack pack, int no) {
		FeatureGrant grant = FloatingFactory.eINSTANCE.createFeatureGrant();
		String fid = feature.getFeatureIdentifier();
		grant.setFeature(fid);
		grant.setCapacity(featureGrantCapacity(fid));
		grant.setIdentifier(String.format("%s#%d", request.identifier(), no)); //$NON-NLS-1$
		grant.setPack(pack);
		grant.setValid(featureGrantPeriod(fid));
		grant.setVivid(featureGrantVivid(fid));
		grant.setVersion(version(feature));
		return grant;
	}

	private int featureGrantCapacity(String feature) {
		return template//
				.flatMap(l -> forFeature(l.getFeatures(), feature)) //
				.map(FeatureGrant::getCapacity)//
				.orElseGet(request::defaultCapacity);
	}

	private ValidityPeriod featureGrantPeriod(String feature) {
		return template//
				.flatMap(l -> forFeature(l.getFeatures(), feature)) //
				.map(g -> EcoreUtil.copy(g.getValid()))//
				.orElseGet(this::period);
	}

	private long featureGrantVivid(String feature) {
		return template//
				.flatMap(l -> forFeature(l.getFeatures(), feature)) //
				.map(FeatureGrant::getVivid)//
				.orElse(60L);
	}

	private Optional<FeatureGrant> forFeature(List<FeatureGrant> all, String feature) {
		return all.stream().filter(g -> feature.equals(g.getFeature())).findFirst();
	}

	private VersionMatch version(LicensePlanFeatureDescriptor feature) {
		VersionMatch version = FloatingFactory.eINSTANCE.createVersionMatch();
		version.setVersion(feature.getMatchVersion());
		version.setRule(feature.getMatchRule());
		return version;
	}

	private String defaultEvaluationType() {
		return new EvaluationType.Hardware().identifier();
	}

}
