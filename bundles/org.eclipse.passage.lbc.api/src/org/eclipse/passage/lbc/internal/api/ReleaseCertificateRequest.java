package org.eclipse.passage.lbc.internal.api;

import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

/**
 * @since 1.0
 */
public interface ReleaseCertificateRequest {

	ExaminationCertificate certificate();

	Requester requester();

}
