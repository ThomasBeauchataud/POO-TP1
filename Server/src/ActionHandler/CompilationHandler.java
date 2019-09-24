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
        log("Treating a compilation");

        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            String compilerArgs = "";

            String[] sourcePathsArray = sourcePaths.split(",");
            for (int i = 0; i < sourcePathsArray.length; i++) {
                sourcePathsArray[i] = System.getProperty("user.dir") + "/" + sourcePathsArray[i];
                compilerArgs += sourcePathsArray[i];
                if (i > sourcePathsArray.length - 2) {
                    compilerArgs += ",";
                }
            }

            compiler.run(null, null, null, compilerArgs);

            return "Success compilation of files " + sourcePaths;
        } catch (Exception e) {
            return "The class " + sourcePaths + "is/are already compiled";
        }
    }

}
