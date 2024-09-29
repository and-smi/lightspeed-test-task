package main.java.ip.address.service.parser;

import main.java.ip.address.service.container.IpFileParsingInfo;
import main.java.ip.address.service.container.UniqueIntIpAddressContainer;

public interface IpAddressParser {
    IpFileParsingInfo parse();
}
