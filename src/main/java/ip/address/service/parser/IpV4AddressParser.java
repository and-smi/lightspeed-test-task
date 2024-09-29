package main.java.ip.address.service.parser;

import main.java.ip.address.service.container.IpFileParsingInfo;
import main.java.ip.address.service.container.UniqueIntIpAddressContainer;
import main.java.ip.address.service.container.UniqueIpV4AddressContainer;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;
import java.util.logging.Logger;

public class IpV4AddressParser implements IpAddressParser {

    private static final char CHARACTER_NUMBER_ZERO = '0';
    private static final int DECIMAL_SYSTEM_CONSTANT = 10;
    private final File file;

    public IpV4AddressParser(String absoluteFilePath) {
        Objects.requireNonNull(absoluteFilePath);
        file = new File(absoluteFilePath);
    }

    @Override
    public IpFileParsingInfo parse() {
        final UniqueIpV4AddressContainer container = new UniqueIpV4AddressContainer();
        long amountOfAddresses = 0L;

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line;

            while ((line = reader.readLine()) != null) {
                ++amountOfAddresses;
                container.add(ipAddressToInt(line));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new IpFileParsingInfo(amountOfAddresses, container);
    }

    private int buildIpAddressPart(int currentIpAddressPart, int currentSymbol) {
        return currentIpAddressPart * DECIMAL_SYSTEM_CONSTANT + (currentSymbol - CHARACTER_NUMBER_ZERO);
    }

    private int shiftParsedIpAddressBytesLeftAndAddNewParsedByte(int currentIpAddressPart, int ipAddress) {
        return (ipAddress << Byte.SIZE) | currentIpAddressPart;
    }

    public int ipAddressToInt(final String sourceIpAddress) {
        int ipAddressAsInt = 0;
        int currentIpAddressPart = 0;
        char currentSymbol;

        for (int i = 0; i < sourceIpAddress.length(); ++i) {
            currentSymbol = sourceIpAddress.charAt(i);

            if (currentSymbol == 46) {
                ipAddressAsInt = shiftParsedIpAddressBytesLeftAndAddNewParsedByte(currentIpAddressPart, ipAddressAsInt);
                currentIpAddressPart = 0;
            } else {
                currentIpAddressPart = buildIpAddressPart(currentIpAddressPart, currentSymbol);
            }
        }

        return  shiftParsedIpAddressBytesLeftAndAddNewParsedByte(currentIpAddressPart, ipAddressAsInt);
    }
}
