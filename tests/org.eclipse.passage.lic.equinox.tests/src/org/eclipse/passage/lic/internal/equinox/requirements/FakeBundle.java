package org.eclipse.passage.lic.internal.equinox.requirements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;

@SuppressWarnings("hiding")
class FakeBundle implements Bundle {

	final String name = "fake name"; //$NON-NLS-1$
	final String vendor = "fake vendor"; //$NON-NLS-1$

	@Override
	public int compareTo(Bundle o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getState() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void start(int options) throws BundleException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void start() throws BundleException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void stop(int options) throws BundleException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void stop() throws BundleException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(InputStream input) throws BundleException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update() throws BundleException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void uninstall() throws BundleException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Dictionary<String, String> getHeaders() {
		Dictionary<String, String> headers = new Hashtable<>();
		headers.put(Constants.BUNDLE_NAME, name);
		headers.put(Constants.BUNDLE_VENDOR, vendor);
		return headers;
	}

	@Override
	public long getBundleId() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getLocation() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ServiceReference<?>[] getRegisteredServices() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ServiceReference<?>[] getServicesInUse() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasPermission(Object permission) {
		throw new UnsupportedOperationException();
	}

	@Override
	public URL getResource(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Dictionary<String, String> getHeaders(String locale) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getSymbolicName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<URL> getResources(String name) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<String> getEntryPaths(String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public URL getEntry(String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getLastModified() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<URL> findEntries(String path, String filePattern, boolean recurse) {
		throw new UnsupportedOperationException();
	}

	@Override
	public BundleContext getBundleContext() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<X509Certificate, List<X509Certificate>> getSignerCertificates(int signersType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Version getVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <A> A adapt(Class<A> type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public File getDataFile(String filename) {
		throw new UnsupportedOperationException();
	}

}
