package AppPackage;
import java.io.File;

import javax.swing.JPanel;

import Parsers.Parser;


public abstract class FormPanel extends JPanel implements Parser {
  private static final long serialVersionUID = 1L;

	@Override
	public void parse(File file) {

	}
}