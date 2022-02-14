/**
 * <p>
 * Package contains persisting implementation for
 * {@linkplain org.eclipse.passage.lic.api.acquire.GrantsTraceService}
 * </p>
 * <p>
 * The implementation has the only entry point:
 * {@linkplain org.eclipse.passage.lic.internal.base.access.StoringGrantTraceService}.
 * </p>
 * <p>
 * As the implementation is stateful by design, it is synchronized. All the
 * synchronization is done by the StoringGrantTraceService. Take care on
 * maintenance.
 * </p>
 */
package org.eclipse.passage.lic.internal.base.access;