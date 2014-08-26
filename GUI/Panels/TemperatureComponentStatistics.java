package Panels;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import Utils.Constants;
import data.Component;
import data.DataManager;
import javafx.scene.layout.BorderPane;

public class TemperatureComponentStatistics extends AbstractComponentStatistics {

	public TemperatureComponentStatistics(BorderPane mainParentPane) {
		super(mainParentPane);
	}

	

	@Override
	protected String getCsvFileLocationAndName() {
		return "C:\\Statistics\\Temperature.xls";
	}


	@Override
	public List<Component> getComponent(Timestamp oldestTS, Timestamp TS) {
		return new ArrayList<>(DataManager.getInstance().getTemprature(oldestTS, TS));
	}

	@Override
	public String getObjectName() {
		return Constants.TEMERATURE_COMPONENT_NAME;
	}
}
