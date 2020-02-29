package com.fnincnellinc.fninccalculator;

import android.app.Application;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import org.acra.sender.HttpSender;

@ReportsCrashes(
        reportType = HttpSender.Type.FORM,
        mode= ReportingInteractionMode.DIALOG,
        resDialogTitle = R.string.crash_dialog_title,
        resDialogText = R.string.crash_message,
        resDialogCommentPrompt = R.string.crash_report_comments,
        formKey = "",
        mailTo  = "neerajrathi81@gmail.com", // my email here,

        customReportContent = {

                ReportField.STACK_TRACE,
                ReportField.PHONE_MODEL,
                ReportField.ANDROID_VERSION,
                ReportField.TOTAL_MEM_SIZE,
                ReportField.USER_CRASH_DATE,
                ReportField.LOGCAT,
                ReportField.SHARED_PREFERENCES,
                ReportField.USER_COMMENT
        }
)

public class ApplicationController extends Application {


	private static ApplicationController sInstance;

	@Override
	public void onCreate() {
		super.onCreate();

		ACRA.init(this);
		// initialize the singleton
		sInstance = this;

	}

	/**
	 * @return ApplicationController singleton instance
	 */
	public static synchronized ApplicationController getInstance() {
		return sInstance;
	}

}
