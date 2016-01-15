package br.com.personal.myevent.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.personal.myevent.EventoFormDialog;
import br.com.personal.myevent.R;
import br.com.personal.myevent.Service.EventoService;
import br.com.personal.myevent.model.Evento;

/**
 * Created by ysmael on 31/12/15.
 */
public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.MyViewHolder> {
    private List<Evento> eventos;
    private LayoutInflater layoutInflater;
    private Context context;
    public EventoAdapter(Context context, List<Evento> pessoas) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.eventos = pessoas;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_evento_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormat = format.format(eventos.get(position).getData());


        holder.dateView.setText(dateFormat);
        holder.valorView.setText(""+eventos.get(position).getEntrada());
        holder.localView.setText(eventos.get(position).getLocal());
        holder.nameView.setText(eventos.get(position).getNome());

        holder.eventoId = eventos.get(position).getId();
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView nameView;
        public TextView localView;
        public TextView valorView;
        public TextView dateView;
        public Button editButton;
        public Button deletButton;

        public long eventoId;
        public int position;

        public MyViewHolder(View view){
            super(view);

            nameView = (TextView) view.findViewById(R.id.tv_evento_nome);
            localView = (TextView) view.findViewById(R.id.tv_evento_local);
            valorView = (TextView) view.findViewById(R.id.tv_evento_valor);
            dateView = (TextView) view.findViewById(R.id.tv_evento_data);

            editButton = (Button) view.findViewById(R.id.button_event_edit);
            deletButton = (Button) view.findViewById(R.id.button_event_edelet);

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventoFormDialog eventoFormDialog = new EventoFormDialog(true, eventos.get(position));

                    AppCompatActivity activity = (AppCompatActivity) context;

                    FragmentManager fm = activity.getSupportFragmentManager();

                    eventoFormDialog.show(fm, "fragment_edit_name");
                }
            });

            deletButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        EventoService.getStance(v.getContext()).deleteById(eventoId);
                        eventos.remove(position);
                        Toast.makeText(v.getContext(), "Evento deletado", Toast.LENGTH_LONG).show();
                        notifyDataSetChanged();
                    } catch (SQLException e) {
                        Toast.makeText(v.getContext(),"Erro ao deletar evento", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

    }
}
