public class HospitalPatient{
	private final String patientid;
	private String fullName;
	private String dateOfBirth;
	private String bloodType;
	private String admissionDate;
	private boolean isDischarge;
	private String [] diagnoses;
	
	public HospitalPatient(String patientid,String fullName,String dateOfBirth,String bloodType,String admissionDate,int max){
		if(patientid.startsWith("PAT-")){
			this.patientid=patientid;
		}
		else{
			this.patientid="PAT-000";
		}
		String tipet="A+,A-,B+,B-,AB+,AB-,0+,0-";
		if(tipet.contains(bloodType)){
			this.bloodType=bloodType;
		}
		else{
			this.bloodType="unknown";
		}
		if(max>=1){
			diagnoses=new String[max];
		}
		else{
			diagnoses=new String[5];
		}
		this.isDischarge=false;
		this.fullName=fullName;
		this.dateOfBirth=dateOfBirth;
		this.admissionDate=admissionDate;
		
	}
	public String getPatientId(){
		return this.patientid;
	}
	public String getBloodType(){
		return bloodType;
	}
	public String getFullName(){
		return fullName;
	}
	public void setName(String name){
		if(name!=null&&!name.trim().isBlank()){
			this.fullName=name;
		}
		else{
			System.out.println("Empty values!");
		}
	}
	public String getBirthDate(){
		return dateOfBirth;
	}
	public void setBirthDate(String db){
		int count=0;
		if(db.length()==10&&db.charAt(2)=='-'&&db.charAt(5)=='-'){
			for(int i=0;i<db.length();i++){
				if(Character.isDigit(db.charAt(i))){
					count++;
				}
			}
			if(count==8){
				this.dateOfBirth=db;
			}
			else{
				System.out.println("Given data isn't written right!");
			}
		}
	}
	public String getAdmissionDate(){
		return this.admissionDate;
	}
	public void setAdmissionDate(String ad){
		int count=0;
		if(ad.length()==10&&ad.charAt(2)=='-'&&ad.charAt(5)=='-'){
			for(int i=0;i<ad.length();i++){
				if(Character.isDigit(ad.charAt(i))){
					count++;
				}
			}
			if(count==8){
				this.admissionDate=ad;
			}
			else{
				System.out.println("Given data isn't written right");
			}
		}
	}
	public boolean getIsDischarge(){
		return isDischarge;
	}
	public void setIsDischarge(boolean isDischarge){
		if(this.isDischarge!=isDischarge){
			this.isDischarge=isDischarge;
			if(isDischarge==false){
				System.out.println("Pacient is re-accepted");
			}
			else{
				System.out.println("Pacient is discharged");
			}
		}
		else{
			System.out.println("Pacient's state is the same");
		}
	}
	public String[] getDiagnoses(){
		return diagnoses.clone();
	}
	
	int currentDiagnoses=0;
	public void addDiagnosis(String d){
		if(isDischarge){
			System.out.println("Pacient has been discharged,no need for diagnosis");
		}
		else if(d==null || d.isBlank()){
			System.out.println("Diagnosis isn't available");
		}
		else if(currentDiagnoses==diagnoses.length-1){
			System.out.println("You can't add other diagnoses");
		}
		else{
			diagnoses[currentDiagnoses]=d;
			currentDiagnoses++;
		}
	}
	public boolean removeDiagnosis(String d){
		for(int i=0;i<diagnoses.length;i++){
			if(d.toLowerCase().equals(diagnoses[i].toLowerCase())){
				for(int j=i;j<diagnoses.length-1;j++){
					diagnoses[j]=diagnoses[j+1];
				}
				diagnoses[diagnoses.length-1]=null;
				currentDiagnoses--;
				return true;
			}
		}return false;
	}
	public boolean hasDiagnosis(String d){
		for(int i=0;i<diagnoses.length;i++){
			if(diagnoses[i]!=null){
				if(diagnoses[i].toLowerCase().equals(d.toLowerCase())){
					return true;
				}
			}
		}return false;
	}
	@Override
	public String toString(){
		return patientid+":"+fullName+"("+bloodType+")"+"-"+"Accepted "+admissionDate+"/"+(isDischarge?"discharged":"active");
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof HospitalPatient){
			HospitalPatient h2=(HospitalPatient) o;
			if(this.patientid.equals(h2.patientid)){
				return true;
			}
		}return false;
	}
	public boolean hasSameBloodType(HospitalPatient h){
		return this.bloodType.equals(h.bloodType);
	}
	public String mostCritical(HospitalPatient h){
		if(h!=null){
			if(this.currentDiagnoses>h.currentDiagnoses){
				return patientid+":"+fullName+" has the most critical state";
			}
			else if(h.currentDiagnoses>this.currentDiagnoses){
				return h.patientid+":"+h.fullName+" has the most critical state";
			}
		}return null;
	}
	public static void main(String [] args){
		HospitalPatient h1=new HospitalPatient("PAT","Rion Syla","02-03-2012","A+","03-12-2025",5);
		HospitalPatient h2=new HospitalPatient("PAT-345","Elma Binaku","04-03-2008","AB-","03-12-2025",15);
		HospitalPatient h3=new HospitalPatient("PAT-345","Enes Hyseni","04-03-2006","0+","03-12-2025",3);
		HospitalPatient h4=new HospitalPatient("PAT-123","Rina Kuqi","02-05-2000","A+","03-12-2025",1);
		System.out.println(h1);
		System.out.println(h2.getBirthDate());
		h3.setName("Alba Aliu");
		System.out.println(h3);
		h3.addDiagnosis("Nausea");
		System.out.println(h3.hasDiagnosis("Nausea"));
		System.out.println(h3.mostCritical(h2));
		h3.removeDiagnosis("Nausea");
		System.out.println(h3.hasDiagnosis("Nausea"));
		System.out.println(h2.equals(h3));
		System.out.println(h1.hasSameBloodType(h4));
	}
}