package org.eclipse.passage.lic.runtime;

/**
 * Information about the outcome of some licensing activity.
 */
public interface LicensingResult {

	/**
	 * The bit mask value <code>0x0</code> for a {@link #getSeverity severity}
	 * indicating the nominal case.
	 */
	int OK = 0x0;

	/**
	 * The bit mask value <code>0x1</code> for a {@link #getSeverity severity}
	 * indicating the information.
	 */
	int INFO = 0x1;

	/**
	 * The bit mask value <code>0x2</code> for a {@link #getSeverity severity}
	 * indicating the warning.
	 */
	int WARNING = 0x2;

	/**
	 * The bit mask value <code>0x4</code> for a {@link #getSeverity severity}
	 * indicating the error.
	 */
	int ERROR = 0x4;

	/**
	 * The bit mask value <code>0x8</code> for a {@link #getSeverity severity}
	 * indicating that the activity was canceled.
	 */
	int CANCEL = 0x8;

	/**
	 * Returns an indicator of the severity of the problem.
	 */
	int getSeverity();

	/**
	 * Returns a message describing the situation.
	 */
	String getMessage();

	/**
	 * Returns the unique identifier of the source.
	 */
	String getSource();

	/**
	 * Returns {@link #getSource source-specific} identity code.
	 */
	int getCode();

	/**
	 * Returns the exception that caused this result, or <code>null</code> if none.
	 */
	Throwable getException();

	/**
	 * Returns the keys for the attached data.
	 */
	Iterable<String> getAttachmentKeys();

	/**
	 * Returns the attachment for the given key or <code>null</code> if none.
	 * 
	 * @param key the key whose associated value is to be returned
	 * @return the attached object or <code>null</code> if none
	 * @see #getAttachmentKeys()
	 */
	Object getAttachment(String key);

	/**
	 * Returns the list of child {@link LicensingResult licensing results}.
	 */
	Iterable<LicensingResult> getChildren();

}
