package com.intechdev.tcommerce.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.intechdev.tcommerce.Classes.GetProvinceAndCity;
import com.intechdev.tcommerce.Models.ItemAddress;
import com.intechdev.tcommerce.My_Addresses.MyAddressContract;
import com.intechdev.tcommerce.R;

import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressHolder> {
    private ItemAddress addresses;
    private Context context;
    private MyAddressContract.View view;
    private Dialog dialog;
    private ArrayAdapter<String> adapterProvince,adapterCity;
    private String province,city;
    private List<String> cities;
    private int userId,flag,finalPrice,totalWeight;

    public AddressAdapter(Context context,ItemAddress addresses, MyAddressContract.View view, ArrayAdapter<String> adapterProvince,int userId,int finalPrice,int totalWeight,int flag) {
        this.context = context;
        this.view = view;

        this.adapterProvince = adapterProvince;
        this.userId = userId;
        this.flag = flag;
        this.addresses = addresses;
        this.finalPrice = finalPrice;
        this.totalWeight = totalWeight;
        cities = new ArrayList<>();

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_address);
        dialog.getWindow().setLayout(1200,1500);
    }

    @NonNull
    @Override
    public AddressHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_address,parent,false);
        return new AddressHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddressHolder holder, final int position) {
        final String addressTitle = addresses.getItems().get(position).getAddressTitle();
        final String tel = addresses.getItems().get(position).getTelTxt();
        holder.txtProvince.setText(addresses.getItems().get(position).getProvince());
        holder.txtCity.setText(addresses.getItems().get(position).getCity());
        holder.txtTel.setText(addresses.getItems().get(position).getTelTxt());
        holder.txtPostalCode.setText(addresses.getItems().get(position).getPostalTxt());
        holder.txtAddress.setText(addresses.getItems().get(position).getAddressTxt());
        holder.spCity.setAdapter(adapterCity);
        holder.spProvince.setAdapter(adapterProvince);




        holder.txtRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.deleteAddress(userId,addresses.getItems().get(position).getId());

            }
        });

        holder.txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.show();

            }
        });

        holder.txtSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = holder.txtProvince.getText().toString() + " - " + holder.txtCity.getText().toString() + " - " + holder.txtAddress.getText().toString() + " - " + " کد پستی:" + holder.txtPostalCode.getText().toString() + " - " + " تلفن: " + holder.txtTel.getText().toString();
                Toast.makeText(context, address, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "قیمت نهای" + finalPrice + "\nوزن کل" + totalWeight, Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnDialogAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = addresses.getItems().get(position).getId();
                ItemAddress add = new ItemAddress();
                List<ItemAddress.SubAddress> subAdds = new ArrayList<>();
                ItemAddress.SubAddress subAdd = new ItemAddress.SubAddress();
                subAdd.setId(id);
                subAdd.setTelTxt(tel);
                subAdd.setAddressTitle(addressTitle);
                subAdd.setAddressTxt(holder.edtAddress.getText().toString());
                subAdd.setPostalTxt(holder.edtPostlCode.getText().toString());
                subAdd.setProvince(province);
                subAdd.setCity(city);
                subAdd.setUsrid(userId);
                subAdds.add(subAdd);
                add.setItems(subAdds);
                view.setEditAddres(add);
                dialog.dismiss();
            }
        });

        holder.spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GetProvinceAndCity getProvinceAndCity = new GetProvinceAndCity();
                province = holder.spProvince.getSelectedItem().toString();
                cities = getProvinceAndCity.getCity(province);
                adapterCity = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,cities);
                adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.spCity.setAdapter(adapterCity);
                holder.spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        city = holder.spCity.getSelectedItem().toString();
                        Toast.makeText(context, city, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return addresses.getItems().size();
    }

    class AddressHolder extends RecyclerView.ViewHolder{
        TextView
                txtProvince,
                txtCity,
                txtPostalCode,
                txtTel,
                txtAddress,
                txtRemove,
                txtEdit,
                txtSelect;
        Spinner spProvince,spCity;
        Button btnDialogAccept;
        TextInputEditText edtPostlCode,edtAddress;

         public AddressHolder(@NonNull View itemView) {
             super(itemView);
             txtProvince = itemView.findViewById(R.id.txt_rvAddress_province);
             txtCity = itemView.findViewById(R.id.txt_rvAddress_city);
             txtPostalCode = itemView.findViewById(R.id.txt_rvAddress_postalCode);
             txtTel = itemView.findViewById(R.id.txt_rvAddress_tel);
             txtAddress = itemView.findViewById(R.id.txt_rvAddress_address);
             txtRemove = itemView.findViewById(R.id.txt_rvAddress_remove);
             txtEdit = itemView.findViewById(R.id.txt_rvAddress_edit);
             txtSelect = itemView.findViewById(R.id.txt_rvAddress_select);
             spProvince = dialog.findViewById(R.id.sp_dialogAddAddress_province);
             spCity = dialog.findViewById(R.id.sp_dialogAddAddress_city);

             btnDialogAccept = dialog.findViewById(R.id.btn_dialogAddAddress_save);

             edtPostlCode = dialog.findViewById(R.id.edt_dialogAddAddress_postalCode);
             edtAddress = dialog.findViewById(R.id.edt_dialogAddAddress_address);

             if(flag == 1){
                 txtSelect.setVisibility(View.VISIBLE);
             }

         }
     }
}
