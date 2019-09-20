package ActionHandler;

import PersistingHandler.PersistingHandlerInterface;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompilationHandler extends ActionHandler implements CompilationHandlerInterface {

    public CompilationHandler(PersistingHandlerInterface persistingHandler) {
        super(persistingHandler);
    }

    @Override
    public String treat(String sourcePaths) {

        System.out.println(sourcePath);
        log("Treating a compilation");

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        ArrayList<String> sourcePathsArray = sourcePaths.split(",");

        compiler.run(null, null, null, sourcePath);

        /*String[] sourcePaths = sourcePath.split(" ");

        int lastSlashIndex = sourcePaths[0].lastIndexOf("/") + 1;
        String sourcePathOption = sourcePaths[0].substring(0, lastSlashIndex);

        List<String> options = new ArrayList<String>();
        options.add("-sourcepath \"" + System.getProperty("user.dir") + sourcePathOption.replace("/", "\\").substring(1) + "\"");

        List<String> classes = new ArrayList<>();

        for (String javaClass: sourcePaths) {
            classes.add(javaClass.substring(lastSlashIndex));
        }

        compiler.getTask(null, null, null, options, classes, null).call();
        */

        return "Success compilation of the file " + sourcePath;
    }

}
