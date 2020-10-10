/**
 * <ul>
 * General license cycle errors
 * <li>1xx - errors begotten by framework/services configuration or behavior
 * <ul>
 * <li>100 - no framework found. Critical.</li>
 * <li>101</li>
 * <li>102 - no service of demanded type. Usually means error in the Framework
 * configuration. Thus, severe.</li>
 * <li>103 - service failed on a morsel. In most cases bearable.</li>
 * <li>104 - service failed because of infrastructure denial. Service cannot
 * finish operation because of IO error, connection troubles or other conditions
 * of this kind.</li>
 * <li></li>
 * <li></li>
 * <li>110 - several implementations of Framework has been found.</li>
 * </ul>
 * </li>
 * <li>2xx - reserved</li>
 * <li>3xx - reserved 4xx - errors begotten by user data (license found,
 * credentials, settings, etc)</li>
 * <li>5xx - global errors, fired by runtime environment</li>
 * <li></li>
 * <li></li>
 * </ul>
 */
package org.eclipse.passage.lic.internal.base.diagnostic.code;
