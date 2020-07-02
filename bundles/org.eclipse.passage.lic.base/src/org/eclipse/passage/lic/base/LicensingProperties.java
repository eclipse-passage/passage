/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.api.io.EncryptionAlgorithm;
import org.eclipse.passage.lic.internal.api.io.EncryptionKeySize;
import org.eclipse.passage.lic.internal.base.conditions.mining.LicensingContentType;
import org.eclipse.passage.lic.internal.base.conditions.mining.SecurityKeyAlgorithm;
import org.eclipse.passage.lic.internal.base.conditions.mining.SecurityKeySize;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

@SuppressWarnings("restriction")
public final class LicensingProperties {

	/**
	 * @deprecated use
	 *             {@link org.eclipse.passage.lic.internal.equinox.requirements.ComponentLicFeatureId}
	 */
	@Deprecated
	public static final String LICENSING_FEATURE_IDENTIFIER = "licensing.feature.identifier"; //$NON-NLS-1$
	/**
	 * @deprecated use
	 *             {@link org.eclipse.passage.lic.internal.equinox.requirements.ComponentLicFeatureVersion}
	 */
	@Deprecated
	public static final String LICENSING_FEATURE_VERSION = "licensing.feature.version"; //$NON-NLS-1$
	/**
	 * @deprecated use
	 *             {@link org.eclipse.passage.lic.internal.equinox.requirements.ComponentLicFeatureName}
	 */
	@Deprecated
	public static final String LICENSING_FEATURE_NAME = "licensing.feature.name"; //$NON-NLS-1$
	public static final String LICENSING_FEATURE_NAME_DEFAULT = BaseMessages
			.getString("LicensingProperties_license_management_title"); //$NON-NLS-1$
	/**
	 * @deprecated use
	 *             {@link org.eclipse.passage.lic.internal.equinox.requirements.ComponentLicFeatureProvider}
	 */
	@Deprecated
	public static final String LICENSING_FEATURE_PROVIDER = "licensing.feature.provider"; //$NON-NLS-1$
	public static final String LICENSING_FEATURE_PROVIDER_DEFAULT = BaseMessages
			.getString("LicensingProperties_eclipse_passage_title"); //$NON-NLS-1$

	/**
	 * Use
	 * {@link org.eclipse.passage.lic.internal.base.restrictions.RestrictionLevel}
	 */
	@Deprecated
	public static final String LICENSING_RESTRICTION_LEVEL = "licensing.restriction.level"; //$NON-NLS-1$
	/**
	 * @deprecated Use
	 *             {@link org.eclipse.passage.lic.internal.base.restrictions.RestrictionLevel.Info}
	 */
	@Deprecated
	public static final String LICENSING_RESTRICTION_LEVEL_INFO = "info"; //$NON-NLS-1$
	/**
	 * @deprecated Use
	 *             {@link org.eclipse.passage.lic.internal.base.restrictions.RestrictionLevel.Warning}
	 */
	@Deprecated
	public static final String LICENSING_RESTRICTION_LEVEL_WARN = "warn"; //$NON-NLS-1$
	/**
	 * @deprecated Use
	 *             {@link org.eclipse.passage.lic.internal.base.restrictions.RestrictionLevel.Error}
	 */
	@Deprecated
	public static final String LICENSING_RESTRICTION_LEVEL_ERROR = "error"; //$NON-NLS-1$
	/**
	 * @deprecated Use
	 *             {@link org.eclipse.passage.lic.internal.base.restrictions.RestrictionLevel.Fatal}
	 */
	@Deprecated
	public static final String LICENSING_RESTRICTION_LEVEL_FATAL = "fatal"; //$NON-NLS-1$
	/**
	 * Use
	 * {@link org.eclipse.passage.lic.internal.base.restrictions.DefaultRestrictionLevel}
	 */
	@Deprecated
	public static final String LICENSING_RESTRICTION_LEVEL_DEFAULT = LICENSING_RESTRICTION_LEVEL_WARN;

	public static final String LICENSING_LEASE_TIME = "licensing.lease.time"; //$NON-NLS-1$
	public static final String LICENSING_EXPIRE_TIME = "licensing.expire.time"; //$NON-NLS-1$

	public static final String LICENSING_CONDITION_TYPE_ID = "licensing.condition.type.id"; //$NON-NLS-1$
	public static final String LICENSING_CONDITION_TYPE_NAME = "licensing.condition.type.name"; //$NON-NLS-1$
	public static final String LICENSING_CONDITION_TYPE_DESCRIPTION = "licensing.condition.type.description"; //$NON-NLS-1$

	public static final String LICENSING_CONDITION_AREA = "licensing.condition.area"; //$NON-NLS-1$
	public static final String LICENSING_CONDITION_URI = "licensing.condition.uri"; //$NON-NLS-1$

	/**
	 * @deprecated use {@link LicensingContentType}
	 */
	@Deprecated
	public static final String LICENSING_CONTENT_TYPE = "licensing.content.type"; //$NON-NLS-1$
	/**
	 * @deprecated use {@link ContentType.Json}
	 */
	@Deprecated
	public static final String LICENSING_CONTENT_TYPE_JSON = "application/json"; //$NON-NLS-1$
	/**
	 * @deprecated use {@link ContentType.Xml}
	 */
	@Deprecated
	public static final String LICENSING_CONTENT_TYPE_XML = "application/xml"; //$NON-NLS-1$

	/**
	 * @deprecated use {@link SecurityKeyAlgorithm}
	 */
	@Deprecated
	public static final String LICENSING_SECURITY_KEY_ALGO = "licensing.security.key.algo"; //$NON-NLS-1$
	/**
	 * @deprecated use {@link EncryptionAlgorithm.RSA}
	 */
	@Deprecated
	public static final String LICENSING_SECURITY_KEY_ALGO_RSA = "RSA"; //$NON-NLS-1$
	/**
	 * @deprecated use {@link SecurityKeySize}
	 */
	@Deprecated
	public static final String LICENSING_SECURITY_KEY_SIZE = "licensing.security.key.size"; //$NON-NLS-1$
	/**
	 * @deprecated use {@link EncryptionKeySize.Of}
	 */
	@Deprecated
	public static final int LICENSING_SECURITY_KEY_SIZE_1024 = 1024;

	public static final String LICENSING_SECURITY_HASH_ALGO = "licensing.security.hash.algo"; //$NON-NLS-1$

	/**
	 * @since 0.6
	 */
	public static final String LICENSING_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'.'SSSZ"; //$NON-NLS-1$

	/**
	 * Use {@link LicensingProperties#getLicensingDateFormat()}
	 * 
	 * @deprecated
	 */
	@Deprecated
	public static final DateFormat DATE_FORMAT = null;

	private LicensingProperties() {
		// block
	}

	public static String toRestrictionLevelProperty(Object object) {
		if (object instanceof String) {
			String level = (String) object;
			if (LICENSING_RESTRICTION_LEVEL_INFO.equalsIgnoreCase(level)) {
				return LICENSING_RESTRICTION_LEVEL_INFO;
			}
			if (LICENSING_RESTRICTION_LEVEL_WARN.equalsIgnoreCase(level)) {
				return LICENSING_RESTRICTION_LEVEL_WARN;
			}
			if (LICENSING_RESTRICTION_LEVEL_ERROR.equalsIgnoreCase(level)) {
				return LICENSING_RESTRICTION_LEVEL_ERROR;
			}
			if (LICENSING_RESTRICTION_LEVEL_FATAL.equalsIgnoreCase(level)) {
				return LICENSING_RESTRICTION_LEVEL_FATAL;
			}
		}
		return LICENSING_RESTRICTION_LEVEL_DEFAULT;
	}

	/**
	 * Creates the licensing date format by pattern
	 * <code>"yyyy-MM-dd'T'HH:mm:ss'.'SSSZ"</code>
	 * 
	 * @return {@link SimpleDateFormat} object as result
	 * 
	 * @since 0.6
	 */
	public static DateFormat getLicensingDateFormat() {
		return new SimpleDateFormat(LICENSING_DATE_FORMAT, Locale.ENGLISH);
	}

	/**
	 * Formats a Date into licensing date format by pattern
	 * <code>"yyyy-MM-dd'T'HH:mm:ss'.'SSSZ"</code>
	 * 
	 * @param date the time value to be formatted into a time string.
	 * @return the formatted time string.
	 * 
	 * @since 0.6
	 */
	public static String getLicensingDateFormat(Date date) {
		DateFormat simpleDateFormat = getLicensingDateFormat();
		return simpleDateFormat.format(date);
	}
}
