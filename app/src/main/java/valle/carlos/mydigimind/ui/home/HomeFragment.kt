package valle.carlos.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import valle.carlos.mydigimind.R
import valle.carlos.mydigimind.databinding.FragmentHomeBinding
import valle.carlos.mydigimind.ui.Task

class HomeFragment : Fragment() {

    private var adaptador: adaptadorTareas?= null
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object{
        var task= ArrayList<Task>()
        var first= true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if(first) {
            fillTasks()
            first= false
        }

        adaptador= adaptadorTareas(root.context, task)

        val gridView: GridView= root.findViewById(R.id.reminders)

        gridView.adapter= adaptador

        return root
    }

    fun fillTasks(){
        task.add(Task("Practice 1", arrayListOf("Tuesday"), "17:30"))
        task.add(Task("Practice 2", arrayListOf("Monday","Sunday"), "17:40"))
        task.add(Task("Practice 3", arrayListOf("Wednesday"), "14:00"))
        task.add(Task("Practice 4", arrayListOf("Saturday"), "11:00"))
        task.add(Task("Practice 5", arrayListOf("Friday"), "13:00"))
        task.add(Task("Practice 6", arrayListOf("Thursday"), "10:40"))
        task.add(Task("Practice 7", arrayListOf("Monday"), "12:00"))

    }

    private class adaptadorTareas: BaseAdapter{
        var tasks= ArrayList<Task>()
        var contexto: Context? = null

        constructor(contexto: Context, tasks: ArrayList<Task>){
            this.contexto= contexto
            this.tasks= tasks
        }

        override fun getCount(): Int {
            return tasks.size
        }

        override fun getItem(p0: Int): Any {
            return tasks[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var task= tasks[p0]
            var inflador= LayoutInflater.from(contexto)
            var vista= inflador.inflate(R.layout.task_view, null)

            var tv_title: TextView= vista.findViewById(R.id.tv_title)
            var tv_time: TextView= vista.findViewById(R.id.tv_time)
            var tv_days: TextView= vista.findViewById(R.id.tv_days)

            tv_title.setText(task.title)
            tv_time.setText(task.time)
            tv_days.setText(task.days.toString())

            return vista
        }

    }
}