package com.google.api.ads.adwords.awreporting;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * Enum to store command line options.
 */
public enum AwReportingOption {
  FILE("file", "aw-report-sample.properties file.", false),
  START_DATE("startDate", "Start date for CUSTOM_DATE Reports (YYYYMMDD)", false),
  END_DATE("endDate", "End date for CUSTOM_DATE Reports (YYYYMMDD)", false),
  DATE_RANGE("dateRange", "ReportDefinitionDateRangeType", false),
  ACCOUNT_IDS_FILE(
      "accountIdsFile",
      "Consider ONLY the account IDs specified on the file to run the report",
      false),
  REPORT_FILE_TYPE(
      "onFileReport",
      "This is an experimental argument, where you can specify the report type, and the processor "
          + "will read the data directly from the CSV file passed on the 'csvReportFile' argument. "
          + "It's mandatory to pass the 'csvReportFile' argument if you pass this argument.",
      false),
  CSV_FILE_INPUT(
      "csvReportFile",
      "This is the path to the CSV report file that will be used to import data directly into "
          + "AwReporting.",
      false);

  private final String argName;
  private final String description;
  private final boolean required;
  
  private AwReportingOption(
      String argName, String description, boolean required) {
    this.argName = argName;
    this.description = description;
    this.required = required;
  }

  public String getArgName() {
    return argName;
  }
  
  public String getOptionValue(CommandLine cmdLine){
    if (cmdLine.hasOption(argName)) {
      return cmdLine.getOptionValue(argName);
    } else {
      return null;
    }
  }
  
  /**
   * Creates the command line options.
   *
   * @return the {@link Options}.
   */
  public static Options createCommandLineOptions() {
    Options options = new Options();
    Option help = new Option("help", "print this message");
    options.addOption(help);

    for (AwReportingOption option : AwReportingOption.values()) {
      options.addOption(
          Option.builder(option.argName)
              .argName(option.argName)
              .desc(option.description)
              .hasArg(true)
              .required(option.required)
              .build());
    }
    
    return options;
  }
}
