package it.univaq.disim.sealab.easier.utils.uml;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.sealab.epsilon.eol.EOLStandalone;
import it.univaq.disim.sealab.epsilon.eol.EasierUmlModel;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;

public class UMLUtil {

    public final static String DEPLOYMENT_VIEW = "deployment_view";
    public final static String STATIC_VIEW = "static_view";
    public final static String DYNAMIC_VIEW = "dynamic_view";

    /**
     * @param modelPath
     * @param elementType
     * @return
     */
    public static List<Object> getElementsInPackage(String modelPath, EClass elementType) {
        try (EasierUmlModel model = EOLStandalone.createUmlModel(modelPath)) {
            List<Object> elements = new ArrayList<>(
                    EcoreUtil.getObjectsByType(model.allContents(), elementType));
            return elements;
        } catch (URISyntaxException | EolModelLoadingException e) {
            throw new RuntimeException(e);
        }
    }

}
