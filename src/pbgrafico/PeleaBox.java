package pbgrafico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

class TextAreaPrintStream extends PrintStream {
    private javax.swing.JTextArea textArea;

    public TextAreaPrintStream(OutputStream out, javax.swing.JTextArea textArea) {
        super(out);
        this.textArea = textArea;
    }

    @Override
    public void println(String x) {
        //Agrega el texto al textArea
        textArea.append(x + "\n");
        // Desplaza el JScrollPane hasta el fondo
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}

class Pelea{ //La clase pelea que hace que los golpes se cuenten
    //Atributo
    private int golpes;   
    //Constructor en donde se inicializa la variable compartida
    public Pelea (){
        golpes=0;
    }  
    public int getGolpes(){ //obtiene el numero de golpes
        return golpes;
    }   
    //Metodo sincronizado
    public synchronized void pegar(Boxeador boxeador){
        boxeador.pegar();
        golpes++;   //suma golpes
        System.out.println("Pegada de "+boxeador.getNombre()+" golpes:"+boxeador.getGolpes()); //print de que boxeador pega a quien
    }
    public void reset(){
        golpes=0;
    }
}

class Boxeador extends Thread{
    private String nombre;
    private Pelea pelea;
    private int golpes;
    private Boxeador rival;
    private Random h = new Random();
    /*
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    */
    javax.swing.JLabel jLabelx;  //el objeto de la clase JLabel es un componente para colocar texto en un contenedor
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel Apar;
    javax.swing.JLabel Agol;
    
    public Boxeador (String nombre, Pelea pelea,javax.swing.JLabel jLabelx, javax.swing.JLabel jLabel3,javax.swing.JLabel Apar, javax.swing.JLabel Agol){
        this.nombre=nombre;
        this.pelea=pelea;
        this.jLabelx=jLabelx;
        this.jLabel3=jLabel3;
        this.Apar= Apar;
        this.Agol = Agol;
    }
    public Boxeador getRival(){ //obtiene el rival
        return rival;
    } 
    public void setRival(Boxeador rival){ //indica cual es el rival
        this.rival=rival;
    }
    public String getNombre(){ //obtiene nombre del boxeador
        return nombre;
    }
    public int getGolpes(){ //obtiene numero de golpes del boxeador
        return golpes;
    }
    public void pegar(){ //suma los golpes
        golpes++;
    }
    public void AnimacionParado(){  //muestra la animacion de estar parado del boxeador
        Apar.setVisible(true);
        Agol.setVisible(false);
    }
    public void AnimacionGolpe(){ //muestra la animacion de gopear del boxeador
        Apar.setVisible(false);
        Agol.setVisible(true);
    }

    public void run(){
        while(pelea.getGolpes() <100){
            //Para que se sepa el nombre del boxeador que esta dando el golpe, quien pega
            pelea.pegar(this);           
            try{
                Thread.sleep(h.nextInt(1000)+1);
                    this.jLabelx.setText(String.valueOf(this.getGolpes()));
            }
            catch(InterruptedException ex){
        }
    }
    if(golpes>50) //quien llegue primero a 50 gana
        jLabel3.setText("Gana "+nombre);
    if(golpes==50) // si ambos llegan a 50 empate
        jLabel3.setText("Empate");
    this.AnimacionParado(); //ejecuta la animacion de parado
    
}  
}
/////////////////////////////////////////////////////
public class PeleaBox extends javax.swing.JFrame {
    Pelea pelea=new Pelea();
    Boxeador boxeador1, boxeador2;

    public PeleaBox() throws LineUnavailableException, IOException {
        ImageIcon icon;
        initComponents();
        setLocationRelativeTo(null);
        boxeador1 = new Boxeador("Ryu", pelea, lblContador1, lblAnuncio, lblRyuIdle, lblRyuGolpe); //asignacion de valores al boxeador
        boxeador2 = new Boxeador("Ken", pelea, lblContador2, lblAnuncio, lblKenIdle, lblKenGolpe);
        boxeador1.AnimacionParado(); //Hice que las animaciones de golpe sean falsas al inicio para que no aparezcan sobrepuestas
        boxeador2.AnimacionParado(); // pone la animacion de de parado en el boxeador
        InputStream inputStream = getClass().getResourceAsStream("/pbgrafico/resources/sfLogo.png");
        icon = new ImageIcon(ImageIO.read(inputStream));
        setIconImage(icon.getImage());
        /*
        boxeador1.RecibeJLabel1(jLabel1);
        boxeador2.RecibeJLabel2(jLabel2);
         */

        TextAreaPrintStream printStream = new TextAreaPrintStream(System.out, txtMensajes);
        System.setOut(printStream);
        System.setErr(printStream);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    //se asigna imagenes a los JLabell y se agrega texto como el contador de golpes seguidos y quien gana
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblKenGolpe = new javax.swing.JLabel();
        lblRyuGolpe = new javax.swing.JLabel();
        lblKenIdle = new javax.swing.JLabel();
        lblRyuIdle = new javax.swing.JLabel();
        lblCombo2 = new javax.swing.JLabel();
        lblCombo1 = new javax.swing.JLabel();
        lblNombrePeleador2 = new javax.swing.JLabel();
        lblNombrePeleador1 = new javax.swing.JLabel();
        lblMiniatura1 = new javax.swing.JLabel();
        lblMiniatura2 = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        lblContador1 = new javax.swing.JLabel();
        lblContador2 = new javax.swing.JLabel();
        lblAnuncio = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMensajes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Street Fighter Hilos");
        setResizable(false);

        jPanel1.setLayout(null);

        lblKenGolpe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbgrafico/resources/kenGolpe.gif"))); // NOI18N
        jPanel1.add(lblKenGolpe);
        lblKenGolpe.setBounds(240, 90, 150, 110);

        lblRyuGolpe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbgrafico/resources/ryuGolpe.gif"))); // NOI18N
        jPanel1.add(lblRyuGolpe);
        lblRyuGolpe.setBounds(210, 80, 100, 130);

        lblKenIdle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbgrafico/resources/kenIdle.gif"))); // NOI18N
        jPanel1.add(lblKenIdle);
        lblKenIdle.setBounds(310, 90, 70, 110);

        lblRyuIdle.setBackground(new java.awt.Color(255, 153, 102));
        lblRyuIdle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbgrafico/resources/ryuIdle.gif"))); // NOI18N
        jPanel1.add(lblRyuIdle);
        lblRyuIdle.setBounds(210, 90, 100, 111);

        lblCombo2.setFont(new java.awt.Font("Segoe UI Black", 2, 11)); // NOI18N
        lblCombo2.setForeground(new java.awt.Color(51, 51, 255));
        lblCombo2.setText(":COMBO");
        jPanel1.add(lblCombo2);
        lblCombo2.setBounds(520, 60, 60, 20);

        lblCombo1.setFont(new java.awt.Font("Segoe UI Black", 2, 11)); // NOI18N
        lblCombo1.setForeground(new java.awt.Color(51, 51, 255));
        lblCombo1.setText("COMBO:");
        jPanel1.add(lblCombo1);
        lblCombo1.setBounds(50, 60, 60, 15);

        lblNombrePeleador2.setBackground(java.awt.Color.cyan);
        lblNombrePeleador2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        lblNombrePeleador2.setForeground(new java.awt.Color(0, 0, 255));
        lblNombrePeleador2.setText("KEN");
        jPanel1.add(lblNombrePeleador2);
        lblNombrePeleador2.setBounds(540, 40, 40, 20);

        lblNombrePeleador1.setBackground(java.awt.Color.cyan);
        lblNombrePeleador1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        lblNombrePeleador1.setForeground(new java.awt.Color(0, 0, 255));
        lblNombrePeleador1.setText("RYU");
        jPanel1.add(lblNombrePeleador1);
        lblNombrePeleador1.setBounds(50, 40, 30, 20);

        lblMiniatura1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbgrafico/resources/bbRyu.jpg"))); // NOI18N
        lblMiniatura1.setText("jLabel5");
        jPanel1.add(lblMiniatura1);
        lblMiniatura1.setBounds(20, 40, 21, 22);

        lblMiniatura2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbgrafico/resources/bbKen.jpg"))); // NOI18N
        lblMiniatura2.setText("jLabel6");
        jPanel1.add(lblMiniatura2);
        lblMiniatura2.setBounds(580, 40, 20, 22);

        btnIniciar.setText("Iniciar Pelea");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        jPanel1.add(btnIniciar);
        btnIniciar.setBounds(250, 230, 120, 23);

        lblContador1.setFont(new java.awt.Font("FZYaoTi", 3, 24)); // NOI18N
        lblContador1.setText("0");
        jPanel1.add(lblContador1);
        lblContador1.setBounds(100, 50, 70, 35);

        lblContador2.setFont(new java.awt.Font("FZYaoTi", 3, 24)); // NOI18N
        lblContador2.setText("0");
        jPanel1.add(lblContador2);
        lblContador2.setBounds(480, 50, 80, 40);

        lblAnuncio.setFont(new java.awt.Font("FZYaoTi", 3, 24)); // NOI18N
        lblAnuncio.setText("      F I G H T");
        jPanel1.add(lblAnuncio);
        lblAnuncio.setBounds(220, 0, 190, 30);

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbgrafico/resources/stage.gif"))); // NOI18N
        lblFondo.setText("jLabel4");
        jPanel1.add(lblFondo);
        lblFondo.setBounds(0, 0, 620, 224);

        txtMensajes.setBackground(new java.awt.Color(37, 37, 38));
        txtMensajes.setColumns(20);
        txtMensajes.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        txtMensajes.setForeground(java.awt.Color.white);
        txtMensajes.setRows(5);
        txtMensajes.setName("txtAreaConsola"); // NOI18N
        jScrollPane1.setViewportView(txtMensajes);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 280, 620, 200);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        boxeador1= new Boxeador("Ryu",pelea,lblContador1,lblAnuncio,lblRyuIdle,lblRyuGolpe); //creacion de objetos boxeador para que al presionar start no se
        boxeador2= new Boxeador("Ken",pelea,lblContador2,lblAnuncio,lblKenIdle,lblKenGolpe);//rompa el programa
        pelea.reset(); //Se resetea el contador en pelea para que inicie bien el contador si se quiere repetir el programa
        lblAnuncio.setText("Fight");
        boxeador1.start(); //inicia el hilo de boxeador
        boxeador2.start();
        boxeador1.AnimacionGolpe(); //Se hace el cambio de animaciones de parado a golpes
        boxeador2.AnimacionGolpe(); // realiza la animacion de golpe
        txtMensajes.setText(""); 
        

        
    }//GEN-LAST:event_btnIniciarActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PeleaBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PeleaBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PeleaBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PeleaBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //Cambiar a estilo Windows
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    new PeleaBox().setVisible(true);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(PeleaBox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(PeleaBox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PeleaBox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(PeleaBox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(PeleaBox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(PeleaBox.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        

        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnuncio;
    private javax.swing.JLabel lblCombo1;
    private javax.swing.JLabel lblCombo2;
    private javax.swing.JLabel lblContador1;
    private javax.swing.JLabel lblContador2;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblKenGolpe;
    private javax.swing.JLabel lblKenIdle;
    private javax.swing.JLabel lblMiniatura1;
    private javax.swing.JLabel lblMiniatura2;
    private javax.swing.JLabel lblNombrePeleador1;
    private javax.swing.JLabel lblNombrePeleador2;
    private javax.swing.JLabel lblRyuGolpe;
    private javax.swing.JLabel lblRyuIdle;
    private javax.swing.JTextArea txtMensajes;
    // End of variables declaration//GEN-END:variables
}
