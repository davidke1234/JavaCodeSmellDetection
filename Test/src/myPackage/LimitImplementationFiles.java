package myPackage;

import java.io.File;
import java.util.List;
import com.puppycrawl.tools.checkstyle.api.*;

public class LimitImplementationFiles extends AbstractFileSetCheck
{
  private static final int DEFAULT_MAX = 100;
  private int fileCount;
  private int max = DEFAULT_MAX;
  public void setMax(int aMax)
  {
    this.max = aMax;
  }

  @Override
  public void beginProcessing(String aCharset)
  {
    super.beginProcessing(aCharset);

    //reset the file count
    this.fileCount = 0;
  }

  public void processFiltered(File file, List<String> aLines)
  {
    this.fileCount++;

    if (this.fileCount > this.max) {
      // log the message
      log(1, "max.files.exceeded", Integer.valueOf(this.max));
      // you can call log() multiple times to flag multiple
      // violations in the same file
    }
  }

@Override
protected void processFiltered(File arg0, FileText arg1) throws CheckstyleException {
	// TODO Auto-generated method stub
	
}
}
      