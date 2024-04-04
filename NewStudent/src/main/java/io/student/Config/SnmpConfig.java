package io.student.Config;

import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration

public class SnmpConfig {

    @Bean
    public Snmp snmp() throws IOException {
        TransportMapping<?> transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);
        transport.listen();
        return snmp;
    }
}
