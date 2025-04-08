//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class LogEntry {
    private final String date;
    private final String ip;
    private final String requestMethod;
    private final int code;

    public LogEntry(String date, String ip, String requestMethod, int code) {
        this.date = date;
        this.ip = ip;
        this.requestMethod = requestMethod;
        this.code = code;
    }

    public String getDate() {
        return this.date;
    }

    public String getIp() {
        return this.ip;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public int getCode() {
        return this.code;
    }

    public String toString() {
        return String.format("Fecha: %s | IP: %s | Método: %s | Código: %d", this.date, this.ip, this.requestMethod, this.code);
    }
}
