package example.mathias.contactinformation.Controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import example.mathias.contactinformation.BE.ContactBE;
import example.mathias.contactinformation.R;

/**
 * Created by Mathias on 27/03/2018.
 */

public class ContactActionController {

    private TextView txtClose, txtName, txtInfo;
    private LinearLayout txtCall, txtSms, txtMail, txtWeb, txtDirection;
    private Dialog myDialog;
    private ContactInformationController info;
    private ContactBE mContact;
    private ContactRecyclerViewAdapter mAdapter;
    private ContactActionController mContactActionController;
    private Context mContext;
    private Intent mIntent;

    public ContactActionController(Context context) {

        this.mContext = context;
        mContactActionController = this;
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.details_pop_up);

        findViewsByIds();
        setOnClickListeners();
    }

    private void findViewsByIds() {
        txtClose = myDialog.findViewById(R.id.txtClose);
        txtCall = myDialog.findViewById(R.id.txtCall);
        txtSms = myDialog.findViewById(R.id.txtSms);
        txtMail = myDialog.findViewById(R.id.txtMail);
        txtWeb = myDialog.findViewById(R.id.txtWeb);
        txtDirection = myDialog.findViewById(R.id.txtDirection);
        txtName = myDialog.findViewById(R.id.txtName);
        txtInfo = myDialog.findViewById(R.id.txtInfo);
    }

    /**
     * Sets Click Listeners on all the clickable views in the PopUp.
     */
    private void setOnClickListeners() {

        // Close
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        // INFORMATION
        txtInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info = new ContactInformationController(view.getContext());
                info.showInfo(mContact, myDialog, mAdapter, mContactActionController);
            }
        });

        // CALL
        txtCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callContact();

                Toast.makeText(view.getContext(), "Calling: " + mContact.getName().toString(), Toast.LENGTH_LONG).show();
            }
        });

        // SMS
        txtSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                smsContact();

                Toast.makeText(view.getContext(), "SMSing to: " + mContact.getName().toString(), Toast.LENGTH_LONG).show();
                Log.d("CALL", "det virker!");
            }
        });

        // MAILING
        txtMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Mailing...", Toast.LENGTH_LONG).show();
                Log.d("CALL", "det virker!");
            }
        });

        // WEBSITE
        txtWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Visiting website...", Toast.LENGTH_LONG).show();
                Log.d("CALL", "det virker!");
            }
        });

        // GET DIRECTION
        txtDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Getting direction...", Toast.LENGTH_LONG).show();
                Log.d("CALL", "det virker!");
            }
        });
    }

    /**
     *  SMS the contact
     */
    private void smsContact() {

        mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + mContact.getPhoneNumber()));

        mContext.startActivity(mIntent);
    }

    /**
     *  Dialing up the contact.
     */
    private void callContact() {

        mIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mContact.getPhoneNumber()));

        mContext.startActivity(mIntent);

    }

    public void showContactActionPopUp(ContactBE contact, ContactRecyclerViewAdapter adapter) {
        mAdapter = adapter;
        mContact = contact;
        txtName.setText(contact.getName());
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void updateContactInformation(ContactBE contact) {
        mContact = contact;
        txtName.setText(mContact.getName());
    }
}
