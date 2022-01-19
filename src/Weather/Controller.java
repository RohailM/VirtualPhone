package Weather;

public class Controller {
	
	private Model model;
	
	public Controller(Model m) {
		this.model = m;
	}
	
	public boolean validCity (String s) {
		this.model.init(s);
		return this.model.valid;
	}
	
	public String getTemp() {
		return model.getCurrentTemp();
	}
	
	public String getDesc() {
		return this.model.getLongDescription();
	}

}

