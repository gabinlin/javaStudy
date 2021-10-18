package top.gabin.springBoot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

@ConfigurationProperties("top.gabin")
@Component
@Validated
public class GabinTopConfig {
    private String name;
    @Email
    private String mail;
    private String securityKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    @Override
    public String toString() {
        return "GabinTopConfig{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", securityKey='" + securityKey + '\'' +
                '}';
    }
}
