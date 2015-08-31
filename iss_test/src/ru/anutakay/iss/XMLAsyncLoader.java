package ru.anutakay.iss;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

public class XMLAsyncLoader extends AsyncTaskLoader<String[]> {

  public XMLAsyncLoader(Context context, Bundle bundle) {
      super(context);
  }

  @Override
  public String[] loadInBackground() {
      String xml = HttpClient.getXML("http://192.168.1.35/list.txt");
      Document document = Parser.parse(xml);
      String[] results = parseDocument(document);
      return results;
  }

  private String[] parseDocument(Document document) {
      NodeList nodes = document.getElementsByTagName("track");
      int length = nodes.getLength();
      String[] results = new String[length];
      for(int i = 0; i < length; i++) {
          results[i] = nodes.item(i).getTextContent();
      }
      return results;
  }

}