package ActionHandler;

public interface CompilationHandlerInterface {

    /**
     * Treat the compilation of Java file(s) and return a confirmation to send threw the socket
     * The Java file(s) are identified by their relative path to the path of sources files
     * @param sourcePaths String
     * @return String
     */
    String treat(String sourcePaths);

}
