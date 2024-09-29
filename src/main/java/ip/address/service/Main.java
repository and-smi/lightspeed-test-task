package main.java.ip.address.service;

import main.java.ip.address.service.parser.IpV4AddressParser;

public class Main {

    //it takes nearly 500 (±100) sec to finish on mac m1 pro
    public static void main(String[] args) {
        var startTime = System.currentTimeMillis();

        final String absoluteFilePath = "/Users/andrew/Downloads/ip_addresses";//paste here path to file
        var result = new IpV4AddressParser(absoluteFilePath).parse();

        var finishTime = System.currentTimeMillis();
        System.out.println(
            String.format(
                "Processed %d amount of addresses. Unique IP v4 addresses: %d. It took: %d sec",
                result.getAmountOfProcessedAddresses(),
                result.getIpV4AddressContainer().size(),
                ( (finishTime - startTime) / 1000 )
            )
        );
    }
}
