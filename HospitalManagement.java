package project;
import java.util.*;


 class Patient {
	private String patientName;
	private int patientAge;
	private String disease;
	private String status;
	
	public Patient(String patientName, int patientAge, String disease, String status) {
		super();
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.disease = disease;
		this.status = status;
	}
	
	public Patient()
	{
		
	}
	
	
	public void setPatientName(String patientName)
	{
		this.patientName=patientName;
	}
	public String getPatientName()
	{
		return patientName;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}


class PatientFactory {
	public static int generateOTP()
	{
		Random random=new Random();
		int otp=random.nextInt(1000,9999);
		return otp;
	}
	public static void displayDetails()
	{
		System.out.println("Select a below option.....");
		System.out.println("1.Enroll a patient:");
		System.out.println("2.Discharge the patient:");
		System.out.println("3.Update the patient details");
		System.out.println("4.Display all patient details");
		System.out.println("5.Exit");
	}

	public static Patient enrollPatient()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the patient name");
		String name=sc.next();
		System.out.println("Enter the patient age");
		int age=sc.nextInt();
		System.out.println("Enter the patient disease");
		String disease=sc.next();
		System.out.println("Enter the patient status");
		String status=sc.next();
		
		return new Patient(name,age,disease,status);
		
	}
	public static void displayAllPatients(Patient p)
	{
		
		//System.out.println(" "+p.getPatientName()+" "+p.getPatientAge()+" "+p.getDisease()+" "+p.getStatus());
		System.out.format("%-10s %-5s %-10s %-10s", p.getPatientName(),p.getPatientAge(),p.getDisease(),p.getStatus());
	}
	
	public static void updateStatus(List<Patient> patientList,String name,String newStatus)
	{
		boolean condn=true;
		for(Patient patient:patientList)
		{
			if(patient.getPatientName().equalsIgnoreCase(name))
			{
				patient.setStatus(newStatus);
				condn=false;
			}
		}
		if(condn)
		{
			System.out.println("Patient not found");
		}
		else
		{
			System.out.println("Updated successfully...");
		}
	}
	
	public static void removePatient(List<Patient> patientList,String name)
	{
		boolean condn=true;
		Iterator<Patient> itr =patientList.iterator();
		while(itr.hasNext())
		{
			Patient patient=itr.next();
			if(patient.getPatientName().equalsIgnoreCase(name))
			{
				itr.remove();
				condn=false;
			}
		}
			
		if(condn)
		{
			System.out.println("Patient not found");
		}
		else
		{
			System.out.println("Removed successfully..");
		}
		}
}


public class HospitalManagement {
	public static void main(String args[]) throws InterruptedException
	{
		String s11="Welcome To Hospital Management System......";
		char ch[]=s11.toCharArray();
		for(char c:ch)
		{
			System.out.print("\u001B[32m"+c+"\u001B[0m");
			Thread.sleep(50);
		}
		//System.out.println("\u001B[35m"+"Welcome To Hospital Management System......"+"\u001B[0m");
		System.out.println();
		System.out.println("Please Login To The Application....");
		System.out.println("Enter Your Mobile Number: ");
		Scanner sc=new Scanner(System.in);
		long mobileNumber=sc.nextLong();
		
		if(mobileNumber<=9999999999l && mobileNumber>=6000000000l)
		{
			int otp=PatientFactory.generateOTP();
			System.out.println("Your OTP is: "+otp);
			System.out.println("Enter the OTP:");
			int inputOTP=sc.nextInt();
			if(otp==inputOTP)
			{
				System.out.println("Login Successful....");
				System.out.println();
				List<Patient> patientList=new ArrayList();
				boolean cond=true;
				while(cond)
				{
					PatientFactory.displayDetails();
					
					int option=sc.nextInt();
					switch(option)
					{
					case 1:
					{
						Patient patient=PatientFactory.enrollPatient();
						patientList.add(patient);
						System.out.println("Patient enrollment is successfull");
						System.out.println();
						break;
					}
					case 2:
					{
						if(patientList.isEmpty())
						{
							System.out.println("No patients present");
						}
						else
						{
						System.out.println("Enter the patient name:");
						String patientName=sc.next();
						PatientFactory.removePatient(patientList, patientName);
						}
						break;
					}
					case 3:
					{
						System.out.println("Enter the patient name:");
						String patientName=sc.next();
						System.out.println("Enter the patient status to be upadated:");
						String newStatus=sc.next();
						PatientFactory.updateStatus(patientList, patientName, newStatus);
						
						break;
					}
					case 4:
					{
						if(patientList.isEmpty())
						{
							System.out.println("No patients present");
						}
						else
						{
						System.out.format("%-10s %-5s %-10s %-10s", "Name","Age","Disease","Status");
						System.out.println();
						System.out.println("____________________________________________");
						System.out.println("____________________________________________");
						for(Patient p:patientList)
						{
							//System.out.format("%-10s %-5s %-10s %-10s", p.getPatientName(),p.getPatientAge(),p.getDisease(),p.getStatus());
							PatientFactory.displayAllPatients(p);
							System.out.println();
							System.out.println("-----------------------------------------");
							
						}
						}
						break;
					}
					case 5:
					{
						cond=false;
						System.out.println("Logout Successfull\nVisit again......");
						break;
					}
					default:
					{
						System.out.println("Invalid Option...\nTry again ");
					}
					}
					
				}
			}
				
			else
			{
				System.out.println("Invalid OTP\n Please Try again Later 😰");
			}
		}
		else
		{
			System.out.println("Incorrect mobile number\nTry again....");
		}
	
	}
}

