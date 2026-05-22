public class HospitalWard{
	private final String wardId;
	private String wardName,department;
	private int totalBeds;
	private HospitalPatient [] patients;
	private String headNurse;
	
	public HospitalWard(String wardId,String wardName,String department,int totalBeds,String headNurse){
		if(wardId.startsWith("WRD-")){
			this.wardId=wardId;
		}
		else{
			this.wardId="WRD-000";
		}
		if(totalBeds>=1){
			this.totalBeds=totalBeds;
		}
		else{
			this.totalBeds=1;
		}
		patients=new HospitalPatient[totalBeds];
		this.wardName=wardName;
		this.department=department;
		this.headNurse=headNurse;
	}
	public String getWardId(){
		return wardId;
	}
	public int getTotalBeds(){
		return totalBeds;
	}
	public String getWardName(){
		return wardName;
	}
	public void setWardName(String wn){
		if(wn!=null&&!wn.isBlank()){
			wardName=wn;
		}
		else{
			System.out.println("Can't be changed the value is NULL");
		}
	}
	public String getDepartment(){
		return department;
	}
	public void setDepartment(String d){
		if(d!=null&&!d.isBlank()){
			department=d;
		}
		else{
			System.out.println("Can't be changed the value is NULL");
		}
	}
	public String getHeadNurse(){
		return headNurse;
	}
	public void setHeadNurse(String hn){
		if(hn!=null&&!hn.isBlank()){
			headNurse=hn;
		}
		else{
			System.out.println("Can't be changed the value is NULL");
		}
	}
	public HospitalPatient [] getPatients(){
		HospitalPatient [] list=new HospitalPatient[patients.length];
		for(int i=0;i<list.length;i++){
			list[i]=patients[i];
		}
		return list;
	}
	int aktiv=0;
	public int getAvailableBeds(){
		for(int i=0;i<patients.length;i++){
			if(patients[i].getIsDischarge()){
				aktiv++;
			}
		}
		return totalBeds-aktiv;
	}
	int indexPatient=0;
	public boolean admitPatient(HospitalPatient patient){
		if(indexPatient<patients.length){
			int count=0;
			for(int i=0;i<patients.length;i++){
				if(patients[i]!=null&&patients[i].equals(patient)){
					count++;
				}
			}
			if(count==0){
				patients[indexPatient]=patient;
				patients[indexPatient].setIsDischarge(false);
				indexPatient++;
				return true;
			}
			else{
				System.out.println("Patiet is currently in hospital");
				return false;
			}
		}
		System.out.println("No empty places in hospital");
		return false;
	}
	public boolean dischargePatient(String patientId){
		for(int i=0;i<patients.length;i++){
			if(patients[i].getPatientId().equals(patientId)){
				patients[i].setIsDischarge(true);
				return true;
			}
		}return false;
	}
	public HospitalPatient findPatient(String patientId){
		for(int i=0;i<patients.length;i++){
			if(patients[i].getPatientId().equals(patientId)){
				return patients[i];
			}
		}return null;
	}
	public HospitalPatient [] getActivePatients(){
		int count=0;
		for(int i=0;i<patients.length;i++){
			if(patients[i]!=null&&patients[i].getIsDischarge()==false){
				count++;
			}
		}
		HospitalPatient [] nonDicharged=new HospitalPatient[count];
		int position=0;
		for(int i=0;i<patients.length;i++){
			if(patients[i]!=null&&patients[i].getIsDischarge()==false){
				nonDicharged[position++]=patients[i];
			}
		}
		return nonDicharged;
	}
	public HospitalPatient [] getPatientsByBloodType(String BloodType){
		int count=0;
		for(int i=0;i<patients.length;i++){
			if(patients[i]!=null&&patients[i].getBloodType().toLowerCase().equals(BloodType.toLowerCase())){
				count++;
			}
		}
		HospitalPatient [] sameBloodType=new HospitalPatient[count];
		int bloodIndex=0;
		for(int i=0;i<patients.length;i++){
			if(patients[i]!=null&&patients[i].getBloodType().toLowerCase().equals(BloodType.toLowerCase())){
				sameBloodType[bloodIndex]=patients[i];
			}
		}
		return sameBloodType;
	}
	public HospitalPatient getMostCriticalPatient(){
		HospitalPatient p1=new HospitalPatient(null,null,null,null,null,0);
		for(int i=0;i<patients.length;i++){
			if(patients[i].getIsDischarge()==false&&p1==null){
				p1=patients[i];
			}
			else if(patients[i].getIsDischarge()==false&&patients[i].getDiagnoses().length>p1.getDiagnoses().length){
				p1=patients[i];
			}
		}
		return p1;
	}
	@Override
	public String toString(){
		return wardId+":"+wardName+"("+department+")"+"-"+"Headnurse:"+headNurse+"\nCapacity:"+aktiv+"/"+totalBeds+" occupied beds"+"|"+aktiv+" are occupied";
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof HospitalWard){
			HospitalWard h2=(HospitalWard) o;
			return this.wardId.equals(h2.wardId);
		}return false;
	}
	public boolean hasSameDepartment(HospitalWard h){
		return this.department.toLowerCase().equals(h.getDepartment());
	}
	public static HospitalWard mostOccupied(HospitalWard a,HospitalWard b){
		if(a!=null&&b!=null){
			double occA=(double)a.aktiv/a.totalBeds;
			double occB=(double)b.aktiv/b.totalBeds;
		if(occA>occB){
			return a;
		}
		else if(occA<occB){
			return b;
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
		System.out.println(h3.hasDiagnosis("Nuasea"));
		System.out.println(h2.equals(h3));
		System.out.println(h1.hasSameBloodType(h4));
		
		System.out.println("----------------------");
		HospitalWard w1=new HospitalWard("WR","Heart department","Cardiology",55,"Arjeta Haxhiu");
		HospitalWard w2=new HospitalWard("WRD-102","Eyes department","Ophtalmology",25,"Labi Musliu");
		HospitalWard w3=new HospitalWard("WRD-202","Heart department","Cardiology",0,"Merita Dallaj");
		
		System.out.println(w1);
		System.out.println(w2.getDepartment());
		System.out.println(w3.getHeadNurse());
		System.out.println(w1.getWardId());
		w1.admitPatient(h1);
		HospitalPatient [] a1=w1.getActivePatients();
		for(HospitalPatient p:a1){
			System.out.println(p);
		}
		HospitalPatient [] a2=w1.getPatientsByBloodType("A+");
		for(HospitalPatient p:a2){
			System.out.println(p);
		}
	}
}
