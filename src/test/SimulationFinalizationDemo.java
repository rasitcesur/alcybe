package test;

import java.util.Calendar;
import java.util.Date;

import alcybe.simulation.Engine;
import alcybe.simulation.data.TaskNode;
import alcybe.simulation.events.DiscreteEvent;
import alcybe.simulation.events.EventType;
import alcybe.simulation.objects.Entity;
import alcybe.simulation.objects.Source;
import alcybe.simulation.objects.Workstation;
import alcybe.simulation.types.TransferableElementContainer;
import alcybe.simulation.types.DispatchingStrategy;
import alcybe.simulation.types.Process;
import alcybe.simulation.types.TransferInfo;

public class SimulationFinalizationDemo {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Engine simEngine=new Engine();
		Workstation m1=new Workstation();
		m1.setIdentifier("M1");
		m1.bufferCapacity=1;
		//List<SimulationEvent> events=new ArrayList<>();

		Entity p1=new Entity(),p2=new Entity(),p3=new Entity(),p4=new Entity();
		
		
		p1.setIdentifier("P1");
		p2.setIdentifier("P2");
		
		
		p1.addTarget(new TransferInfo(new Process(new TaskNode(m1,"40"))));
		p2.addTarget(new TransferInfo(new Process(new TaskNode(m1,"25"))));
		
		
		/**
		p1.addTarget(new TransferInfo(new Bom(new TaskNode(m1,"40")), new Bom(new TaskNode(m3,"30"))));
		//p1.addTarget(new TransferInfo(new Bom(new TaskNode(m3,"30"))));
		p2.addTarget(new TransferInfo(new Bom(new TaskNode(m2,"25")) , new Bom(new TaskNode(m3,"20"))));
		//p2.addTarget(new TransferInfo(new Bom(new TaskNode(m3,"20"))));
		*/
		
		Source src=new Source();
		src.setIdentifier("S1\t");
		src.addElement(p1);
		src.addElement(p2);
		src.timeBetweenArrivals="0";
		src.arrivalTreshold=2;
		//Date beginDate=getDate();
		simEngine.initEvent(new DiscreteEvent[]{new DiscreteEvent(0,null,new TaskNode(src), 
				new Date(2018, 5, 28, 8, 0),//beginDate, 
				EventType.ArrivalEvent)}, new Date(2018, 5, 29, 8, 0));//getDate(beginDate,1));
		long begin=System.nanoTime();
		simEngine.run();
		long end=System.nanoTime();
		System.out.println(((double)end-begin)/1E9d);
	}
	
	public static Date getDate(){
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	
	public static Date getDate(Date begin, int timespan){
		Calendar cal = Calendar.getInstance();
		cal.setTime(begin);
		cal.add(Calendar.YEAR, timespan);
		return cal.getTime();
	}
}