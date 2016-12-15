package bridge;

import extractInformation.IExtractor;

public class Director {
  private IExtractor extractor;
  private String extract;

  public void direct(final String extractor) {
    this.extractor = ExtractorFactory.getExtractor(extractor);
    this.extract = extractor;
  }

  public IExtractor getExtractor() {
    return extractor;
  }

  public void setExtractor(final IExtractor extractor) {
    this.extractor = extractor;
  }

  public String getExtract() {
    return extract;
  }

  public void setExtract(final String extract) {
    this.extract = extract;
  }
}
