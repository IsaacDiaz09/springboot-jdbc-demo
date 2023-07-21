package dev.isaac.tests.springjdbc.domain;

public class ConnectionDetails {

    private static final String JDBC_PREFIX = "jdbc:";
    private static final String USER_PLACEHOLDER = "#user";
    private static final String PASSWORD_PLACEHOLDER = "#password";

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username != null ? username : "";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password != null ? password : "";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String parseConnectionString() {
        return JDBC_PREFIX.concat(url)
                .replaceFirst(USER_PLACEHOLDER, getUsername())
                .replaceFirst(PASSWORD_PLACEHOLDER, getPassword());
    }
}
