package base;

import java.io.IOException;

public interface NonSeleniumGenericKeywords {

	public int takeSnap() throws IOException;

    public String getProperty(String propertyName);
}
