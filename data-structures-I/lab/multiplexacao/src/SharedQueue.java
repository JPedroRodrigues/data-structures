public class SharedQueue {
     private static final int TAM_DEFAULT = 100;
     private int inicio, fim, qtde;
     private FluxData e[ ];
     public SharedQueue(int tamanho) {// construtor 1 (com tamanho)
         this.inicio = this.fim = this.qtde = 0;
         e = new FluxData[tamanho];
     }

     public SharedQueue() {
         this(TAM_DEFAULT);
     }


     public boolean isEmpty() {
         return (qtde == 0);
     }


     public boolean isFull() {
         return (qtde == e.length);
     }


     public void enqueue(FluxData e) {
         if (! isFull( )){
             this.e[this.fim++] = e;
             this.fim = this.fim % this.e.length;
             this.qtde++;
         }
         else
             System.out.println("Overflow - Estouro de Fila");
     }

     public FluxData dequeue() {
         FluxData aux;
         if (!isEmpty( )){
             aux =  this.e[ this.inicio];
             this.inicio = ++this.inicio % this.e.length;
             this.qtde--;
             return aux;
         }else{
             System.out.println("underflow - Esvaziamento de Fila");
             return new FluxData();
         }
     }

     public FluxData front() {
         if (! isEmpty())
             return e[inicio];
         else{
             System.out.println("underflow - Esvaziamento de Fila");
             return new FluxData();
         }
     }
     public FluxData rear() {
         if (! isEmpty()){
             int pfinal;
             if (this.fim != 0) pfinal = this.fim - 1;
             else pfinal = this.e.length - 1;
             return this.e[pfinal];
         }else{
             System.out.println("underflow - Esvaziamento de Fila");
             return new FluxData();
         }
     }

     public	int totalElementos(){
         return qtde;
     }

     @Override
     public String toString() {

         int indiceNovo = (inicio + qtde) % e.length;

         StringBuilder sb = new StringBuilder();

         sb.append("\nConteudo da Fila': [ ");
         if (qtde != 0) {
             if (indiceNovo <= inicio) {
                 for (int i = inicio; i < e.length; ++i)
                     sb.append("[" + e[i] + "] ");
                 for (int i = 0; i < indiceNovo; ++i)
                     sb.append("[" + e[i] + "] ");
             } else {
                 for (int i = inicio; i < indiceNovo; ++i)
                     sb.append("[" + e[i] + "] ");
             }
         }
         sb.append("]");
         return sb.toString();
     }
}
