package appli;

import java.awt.BorderLayout;
import java.util.EnumSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.CalendarAdapter;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.DayOfWeekFormat;
import com.mindfusion.scheduling.ItemMouseEvent;
import com.mindfusion.scheduling.WeekRangeHeaderStyle;
import com.mindfusion.scheduling.model.*;
import com.mindfusion.scheduling.standardforms.AppointmentForm;


public class RDV extends JFrame
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					new RDV().setVisible(true);
				}
				catch (Exception exp)
				{
				}
			}
		});
	}

	protected RDV()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 400);
		setTitle("Rendez-vous");

		calendar = new Calendar();
		getContentPane().add(calendar, BorderLayout.CENTER);

		calendar.beginInit();
		calendar.setCurrentView(CalendarView.WeekRange);
		calendar.setDate(new DateTime(2022, 01, 1));
		calendar.setEndDate(new DateTime(2022, 03, 1));
		calendar.setInteractiveItemType(MyApp.class);
		calendar.getWeekRangeSettings().setHeaderStyle(EnumSet.of(WeekRangeHeaderStyle.Title));
		calendar.getWeekRangeSettings().setDayOfWeekFormat(DayOfWeekFormat.Full);
		calendar.setEnableDragCreate(true);
		calendar.endInit();

		calendar.addCalendarListener(new CalendarAdapter()
		{
			private void showForm(Item item) {
				AppointmentForm form = new AppointmentForm(calendar.getSchedule());
				form.setAppointment((Appointment)item);
				form.setVisible(true);
			}
			@Override
			public void itemClick(ItemMouseEvent e)
			{
				showForm(e.getItem());
			}
			@Override
			public void itemCreated(ItemEvent e) {
			calendar.getSelection().reset();
			calendar.getSelection().add(e.getItem().getStartTime(),
					e.getItem().getEndTime());
			
		showForm(e.getItem());
			}
			});
	}


	private static final long serialVersionUID = 1L;

	private Calendar calendar;

	public void open() {
		// TODO Auto-generated method stub
		
	}
}