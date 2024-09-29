package main.java.ip.address.service.container;

public class IpFileParsingInfo {

    public long getAmountOfProcessedAddresses() {
        return amountOfProcessedAddresses;
    }

    public UniqueIpV4AddressContainer getIpV4AddressContainer() {
        return ipV4AddressContainer;
    }

    private final long amountOfProcessedAddresses;
    private final UniqueIpV4AddressContainer ipV4AddressContainer;

    public IpFileParsingInfo(long amountOfProcessedAddresses, UniqueIpV4AddressContainer ipV4AddressContainer) {
        this.amountOfProcessedAddresses = amountOfProcessedAddresses;
        this.ipV4AddressContainer = ipV4AddressContainer;
    }
}
