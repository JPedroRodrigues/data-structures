public class FluxData {

    private int flux;
    private int data;

    public FluxData(int flux , int data){
        this.flux = flux;
        this.data = data;
    }

    public  FluxData(){
        this.flux = 0;
        this.data=0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(flux)
                .append(",")
                .append(" ")
                .append(data);

        return sb.toString();
    }
}
