package ru.anutakay.iss;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

public class XMLAsyncLoader extends AsyncTaskLoader<String[]> {

  String format;

  public XMLAsyncLoader(Context context, Bundle bundle) {
    super(context);
  }

  @Override
  public String[] loadInBackground() {
      HttpClient.getXML("http://192.168.1.35/list.txt");
      String[] results = new String[1];
      results[0] = "111";
      return results;
  }

}