package org.elsys.internetprogramming.trafficspy;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class MyAlertDialog {
	public static void createAlertDialog(Context context, String title,
			String message, String positiveText,
			final AlertDialogButtonOnClickListener positiveListener,
			String negativeText,
			final AlertDialogButtonOnClickListener negativeListener) {
		
		Builder alertDialog = new AlertDialog.Builder(context)
				.setTitle(title)
				.setCancelable(false)
				.setPositiveButton(positiveText, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						positiveListener.onClick(dialog);
						
					}
				});
				
		if (message != null) {
			alertDialog.setMessage(message);
		}
		
		if (negativeListener != null) {
			alertDialog.setNegativeButton(negativeText, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					negativeListener.onClick(dialog);
					
				}
			});
		}
		
		alertDialog.create().show();
	}
	
	public static void createAlertDialog(Context context, String title,
			String message, String positiveText,
			AlertDialogButtonOnClickListener positiveListener) {
		createAlertDialog(context, title, message, positiveText, positiveListener, null, null);
	}
}