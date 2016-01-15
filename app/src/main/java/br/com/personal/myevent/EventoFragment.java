package br.com.personal.myevent;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.personal.myevent.Service.EventoService;
import br.com.personal.myevent.adapter.EventoAdapter;
import br.com.personal.myevent.model.Evento;
import br.com.personal.myevent.util.GridSpacingItemDecoration;


public class EventoFragment extends Fragment {

    private RecyclerView recyclerView;
    private EventoAdapter adapter;
    private  List<Evento> eventos = null;

    private EventoService eventoService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_evento, container, false);

        eventoService = EventoService.getStance(getActivity().getApplicationContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_eventos);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());


        //Set Layout in Recycle View
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        int spanCount = 2;
        int spacing = 30;
        boolean includeEdge = false;

        //Setar o adapter no recycle view
        MainActivity activity = (MainActivity) getActivity();

        try {
           eventos = eventoService.listarEventos();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(eventos == null){
                eventos = new ArrayList<>();
            }
            adapter = new EventoAdapter(getActivity(), eventos);
            recyclerView.setAdapter(adapter);

        }
        return view;
    }

    public void addItem(Evento item) {
        if(!eventos.contains(item)){
            eventos.add(item);
        }
        adapter.notifyDataSetChanged();
    }
}
