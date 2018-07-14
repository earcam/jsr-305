package com.github.spotbugs.jsr305.annotation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.github.spotbugs.jsr305.annotation.concurrent.ThreadSafe;
import com.github.spotbugs.jsr305.annotation.meta.When;

public class JavaVersionIntegrationTest {

	private static final int JAVA6_CLASSFILE_MAJOR_VERSION = 50;
	
	private static final int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;
	private static final int DEFAULT_BUFFER_SIZE = 8192;

	private static final byte[] CAFEBABE = { (byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE };


	@Test
	public void ensureClassesAreTargetedForJavaVersionEight() throws IOException
	{
		assertJavaMajorVersion(CheckForNull.class, JAVA6_CLASSFILE_MAJOR_VERSION);
		assertJavaMajorVersion(ThreadSafe.class, JAVA6_CLASSFILE_MAJOR_VERSION);
		assertJavaMajorVersion(When.class, JAVA6_CLASSFILE_MAJOR_VERSION);
	}


	private void assertJavaMajorVersion(Class<?> type, int classFileMajorVersion) throws IOException
	{
		String name = type.getCanonicalName().replace('.', '/') + ".class";
		ClassLoader loader = type.getClassLoader();

		try(InputStream inputStream = loader.getResourceAsStream(name)) {
			byte[] bytes = readAllBytes(inputStream);

			assertMagicNumber(bytes);
			assertTargetVersion(bytes, classFileMajorVersion);
		}
	}


	private static void assertMagicNumber(byte[] compiled)
	{
		assertThat(compiled[0], is(CAFEBABE[0]));
		assertThat(compiled[1], is(CAFEBABE[1]));
		assertThat(compiled[2], is(CAFEBABE[2]));
		assertThat(compiled[3], is(CAFEBABE[3]));
	}


	private void assertTargetVersion(byte[] compiled, int classFileMajorVersion)
	{
		int inClassFile = compiled[7];
		assertThat(inClassFile, is(classFileMajorVersion));
	}


	private static byte[] readAllBytes(InputStream in) throws IOException
	{
		byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
		int capacity = buf.length;
		int nread = 0;
		int n;
		for(;;) {
			while((n = in.read(buf, nread, capacity - nread)) > 0)
				nread += n;

			if(n < 0)
				break;

			if(capacity <= MAX_BUFFER_SIZE - capacity) {
				capacity = capacity << 1;
			} else {
				if(capacity == MAX_BUFFER_SIZE)
					throw new OutOfMemoryError("Required array size too large");
				capacity = MAX_BUFFER_SIZE;
			}
			buf = Arrays.copyOf(buf, capacity);
		}
		return (capacity == nread) ? buf : Arrays.copyOf(buf, nread);
	}
}
