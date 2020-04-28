package com.framgia.sample.calendardayview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static android.content.Context.MODE_PRIVATE;


public class BackgroundWorker extends AsyncTask<String,Void,String[]> {
    Context context;
    AlertDialog alertDialog;
    BackgroundWorker (Context ctx) {
        context = ctx;
    }


    @Override
    protected String[] doInBackground(String... params) {
        String type = params[0];
        String login_url = "https://www-student.cse.buffalo.edu/CSE442-542/2020-spring/cse-442u/login.php";
        String account_register_url = "https://www-student.cse.buffalo.edu/CSE442-542/2020-spring/cse-442u/account-register.php";
        String save_calendar_event_url = "https://www-student.cse.buffalo.edu/CSE442-542/2020-spring/cse-442u/save-event.php";
        String register_event_url = "https://www-student.cse.buffalo.edu/CSE442-542/2020-spring/cse-442u/event-register.php";
        String retrieve_events_url = "https://www-student.cse.buffalo.edu/CSE442-542/2020-spring/cse-442u/retrieve-events.php";
        if(type.equals("login")) {
            try {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return new String[]{result, type, user_name};
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(type.equals("account_register")) {
            try {
                String user_name = params[1];
                String password = params[2];
                String e_mail = params[3];
                URL url = new URL(account_register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        + URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("e-mail","UTF-8")+"="+URLEncoder.encode(e_mail,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return new String[]{result, type};
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("addCalendarEvent")) {
            try {
                String user_name = params[1];
                String class_name = params[2];
                String start_hour = params[3];
                String start_min = params[4];
                String end_hour = params[5];
                String end_min = params[6];
                String location = params[7];

                URL url = new URL(save_calendar_event_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        + URLEncoder.encode("event_name","UTF-8")+"="+URLEncoder.encode(class_name,"UTF-8")+"&"
                        + URLEncoder.encode("start_hour","UTF-8")+"="+URLEncoder.encode(start_hour,"UTF-8")+"&"
                        + URLEncoder.encode("start_min","UTF-8")+"="+URLEncoder.encode(start_min,"UTF-8")+"&"
                        + URLEncoder.encode("end_hour","UTF-8")+"="+URLEncoder.encode(end_hour,"UTF-8")+"&"
                        + URLEncoder.encode("end_min","UTF-8")+"="+URLEncoder.encode(end_min,"UTF-8")+"&"
                        + URLEncoder.encode("location","UTF-8")+"="+URLEncoder.encode(location,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return new String[]{result, type};
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("event_register")) {
            try {
                String Event_name = params[1];
                String Location = params[2];
                String Start = params[3];
                String End = params[4];
                String RSVP = params[5];
                String Promote = params[6];
                String Description = params[7];

                URL url = new URL(register_event_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("event_name","UTF-8")+"="+URLEncoder.encode(Event_name,"UTF-8")+"&"
                        + URLEncoder.encode("location","UTF-8")+"="+URLEncoder.encode(Location,"UTF-8")+"&"
                        + URLEncoder.encode("start_time","UTF-8")+"="+URLEncoder.encode(Start,"UTF-8")+"&"
                        + URLEncoder.encode("end_time","UTF-8")+"="+URLEncoder.encode(End,"UTF-8")+"&"
                        + URLEncoder.encode("rsvp","UTF-8")+"="+URLEncoder.encode(RSVP,"UTF-8")+"&"
                        + URLEncoder.encode("promote","UTF-8")+"="+URLEncoder.encode(Promote,"UTF-8")+"&"
                        + URLEncoder.encode("description","UTF-8")+"="+URLEncoder.encode(Description,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return new String[]{result, type};
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("retrieve_events")) {
            try {
                String checkPromoted = "0";

                URL url = new URL(retrieve_events_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("check_promoted","UTF-8")+"="+URLEncoder.encode(checkPromoted,"UTF-8")+"&";

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                //do it again
                checkPromoted = "1";

                URL url1 = new URL(retrieve_events_url);
                HttpURLConnection httpURLConnection1 = (HttpURLConnection)url1.openConnection();
                httpURLConnection1.setRequestMethod("POST");
                httpURLConnection1.setDoOutput(true);
                httpURLConnection1.setDoInput(true);
                OutputStream outputStream1 = httpURLConnection1.getOutputStream();
                BufferedWriter bufferedWriter1 = new BufferedWriter(new OutputStreamWriter(outputStream1, "UTF-8"));
                String post_data1 = URLEncoder.encode("check_promoted","UTF-8")+"="+URLEncoder.encode(checkPromoted,"UTF-8")+"&";

                bufferedWriter1.write(post_data1);
                bufferedWriter1.flush();
                bufferedWriter1.close();
                outputStream1.close();
                InputStream inputStream1 = httpURLConnection1.getInputStream();
                BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1,"iso-8859-1"));
                String result1="";
                String line1="";
                while((line1 = bufferedReader1.readLine())!= null) {
                    result1 += line1;
                }
                bufferedReader1.close();
                inputStream1.close();
                httpURLConnection1.disconnect();
                return new String[]{result, type, result1};
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {


        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
    }

    @Override
    protected void onPostExecute(String[] values) {

        String result = values[0];
        String type = values[1];
        if (type.equals("login") || type.equals("account_register")) {
            alertDialog.setMessage(result);
            alertDialog.show();
        }

        //if user inputs the right info, go to the main page(login successful)
        if(result.equals("Login success")) {
            String user_Name = values[2];
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("user_name", user_Name);
            context.startActivity(intent);

        }

        if(type.equals("retrieve_events")) {
            String result2 = values[2];
            String fileName = "NonPromotedEvents.txt";

            FileOutputStream fos = null;
            BufferedWriter bw = null;
            try {
                fos = context.openFileOutput(fileName, MODE_PRIVATE);
                bw = new BufferedWriter(new OutputStreamWriter(fos));
                bw.write(result);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(fos != null){
                    try {
                        bw.close();
                        fos.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //do it again for promoted events
            fileName = "PromotedEvents.txt";
            try {
                fos = context.openFileOutput(fileName, MODE_PRIVATE);
                bw = new BufferedWriter(new OutputStreamWriter(fos));
                bw.write(result2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(fos != null){
                    try {
                        bw.close();
                        fos.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }




//            alertDialog.setMessage(result);
//            alertDialog.show();
//            Intent intent = new Intent(context, EventFinderPage.class);
//            intent.putExtra("events", result);
//            context.startActivity(intent);
        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}