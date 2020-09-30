package Helper;

import javax.swing.JOptionPane;

public class Helper {
	public static void showMsg(String str) {
		String msg;
		
		switch(str) {
		case "fill":
			msg = "Please Fill All The Blanks!";
			break;
		case "success":
		msg="Transaction Is Successful!";
				break;
			default:
				msg = str;
		}
		
		JOptionPane.showMessageDialog(null,msg,"Message", JOptionPane.INFORMATION_MESSAGE);
		
	}

}
