package ActionHandler;

import PersistingHandler.PersistingHandlerInterface;

import java.io.Serializable;

public class CreationHandler extends ActionHandler implements CreationHandlerInterface {

	public CreationHandler(PersistingHandlerInterface persistingHandler) {
		super(persistingHandler);
	}

	@Override
	public String treat(Class objectClass, String identification) {

		log("Treating a creation");
		try {
			this.persistingHandler.persist((Serializable) objectClass.newInstance(), identification);
			return "Success while creating a new instance : " + identification;
		} catch (Exception e) {
			log(e.getMessage());
			return "Impossible to create a new instance of " + objectClass.getName() + identification + " : "
					+ e.getMessage();
		}
	}

}
