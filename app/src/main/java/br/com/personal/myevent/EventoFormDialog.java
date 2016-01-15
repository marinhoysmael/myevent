package br.com.personal.myevent;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import br.com.personal.myevent.Service.EventoService;
import br.com.personal.myevent.model.Evento;

/**
 * Created by ysmael on 31/12/15.
 */
public class EventoFormDialog extends  DialogFragment{

    private DatePicker datePicker;
    private EditText nameView;
    private EditText valorView;
    private EditText localView;

    private Button addEventoButton;

    private boolean isEditar = false;
    private Evento evento = null;


    public EventoFormDialog(boolean isEditar, Evento evento){
        this.isEditar = isEditar;
        this.evento = evento;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_form_evento, container);

        getDialog().setTitle("Novo Evento");

        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        nameView = (EditText) view.findViewById(R.id.evennto_name);
        valorView = (EditText) view.findViewById(R.id.evento_valor);
        localView = (EditText) view.findViewById(R.id.evento_local);

        addEventoButton = (Button) view.findViewById(R.id.new_evento_button);

        addEventoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year =  datePicker.getYear();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);


                String nome = nameView.getText().toString();
                String valorText = valorView.getText().toString();
                String local = localView.getText().toString();
                try {
                    if(evento == null){
                        evento = new Evento();
                    }

                    evento.setNome(nome);
                    evento.setData(calendar.getTime());
                    evento.setLocal(local);


                    double valor = Double.parseDouble(valorText);
                    evento.setEntrada(valor);


                    if(isEditar){
                        EventoService.getStance(getActivity()).updateBuild(evento);
                        Toast.makeText(v.getContext(), "Evento atualizado", Toast.LENGTH_LONG).show();

                    }else{
                        EventoService.getStance(getActivity()).create(evento);
                    }
                    MainActivity mainActivity = (MainActivity) getActivity();

                    mainActivity.getEventoFragment().addItem(evento);

                    dismiss();

                }catch (NumberFormatException e){
                    evento.setEntrada(0);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        if(isEditar){
            addEventoButton.setText("Editar Evento");

            if(evento != null){
                Date date = evento.getData();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                datePicker.updateDate(year, month, day);

                nameView.setText(evento.getNome());
                valorView.setText(""+evento.getEntrada());
                localView.setText(evento.getLocal());
            }

        }
        return view;
    }


}
