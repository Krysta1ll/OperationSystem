package OS.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;

public class mainController {


@FXML
void initialize(){

  priority_1.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");
    priority_2.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");
    priority_3.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");
    priority_4.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");
    CPU.getItems().addAll("1","2","3","4","5","6","7","8");



}



    @FXML
    private ChoiceBox<String> CPU;

    @FXML
    private ListView<?> PCB_end;

    @FXML
    private Label Title;

    @FXML
    private Label Title1;

    @FXML
    private Label Title11;

    @FXML
    private Label Title111;

    @FXML
    private Button activeBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private Button confirmBtn;

    @FXML
    private Label cpuLabel;

    @FXML
    private ListView<String> cpuList_hanging;

    @FXML
    private Button hangUpBtn;

    @FXML
    private Button nextBtn;

    @FXML
    private ListView<?> pcb_ready;

    @FXML
    private ListView<String> prioprityList_ready;

    @FXML
    private ListView<String> prioprity_hanging;

    @FXML
    private ListView<String> priorityList;

    @FXML
    private ChoiceBox<String> priority_1;

    @FXML
    private ChoiceBox<String> priority_2;

    @FXML
    private ChoiceBox<String> priority_3;

    @FXML
    private ChoiceBox<String> priority_4;

    @FXML
    private Label priorityLable;

    @FXML
    private ListView<String> runningTimeList;

    @FXML
    private ListView<String> runningTimeList_ready;

    @FXML
    private TextField runningTime_1;

    @FXML
    private TextField runningTime_2;

    @FXML
    private TextField runningTime_3;

    @FXML
    private TextField runningTime_4;

    @FXML
    private ListView<String> scheList;

    @FXML
    private ListView<String> scheList_end;

    @FXML
    private ListView<String> scheList_ready;

    @FXML
    private TextField scheduling_1;

    @FXML
    private TextField scheduling_2;

    @FXML
    private TextField scheduling_3;

    @FXML
    private TextField scheduling_4;

    @FXML
    private Label timeLable;

    @FXML
    private ListView<String> timeList_hanging;


    //cpu??????????????????
    void addTo(String schedule,int runningTime,int priority){
        cpuLabel.setText(schedule);
        priorityLable.setText(String.valueOf(priority));
        timeLable.setText(String.valueOf(runningTime));




    }
    @FXML

    void clearBtnAction(ActionEvent event) {
        scheduling_1.clear();scheduling_2.clear();scheduling_3.clear();scheduling_4.clear();
        runningTime_1.clear();runningTime_2.clear();runningTime_3.clear();runningTime_4.clear();
        priority_1.setValue(null);priority_2.setValue(null);priority_3.setValue(null);priority_4.setValue(null);
    }

    //????????????????????????
    @FXML
    void confirmBtnAction(ActionEvent event){
//        ?????????????????????????????????
        addSchedule addSchedule=new addSchedule();
        addSchedule.schedules[0]=scheduling_1.getText();addSchedule.schedules[1]=scheduling_2.getText();addSchedule.schedules[2]=scheduling_3.getText();addSchedule.schedules[3]=scheduling_4.getText();
        addSchedule.priorities[0]=Integer.parseInt(priority_1.getValue());addSchedule.priorities[1]=Integer.parseInt(priority_2.getValue());addSchedule.priorities[2]=Integer.parseInt(priority_3.getValue());addSchedule.priorities[3]=Integer.parseInt(priority_4.getValue());
        addSchedule.runningTimes[0]=Integer.parseInt(runningTime_1.getText()); addSchedule.runningTimes[1]=Integer.parseInt(runningTime_2.getText()); addSchedule.runningTimes[2]=Integer.parseInt(runningTime_3.getText()); addSchedule.runningTimes[3]=Integer.parseInt(runningTime_4.getText());
//       ?????????????????????????????????
       scheList.getItems().addAll(addSchedule.schedules);
       for(int i=0;i<4;i++) {
           runningTimeList.getItems().addAll(String.valueOf(addSchedule.runningTimes[i]));
           priorityList.getItems().addAll(String.valueOf(addSchedule.priorities[i]));

       }

   }

   //????????????????????????
    @FXML
    void runningBtnAction (ActionEvent event) {

        //  ??????cpu??????
        int cpu=Integer.parseInt(CPU.getValue());


        //???????????????????????????????????????????????????
          String[] priorities_ready=new String[priorityList.getItems().size()];
          String []runningTimes_ready=new String[priorityList.getItems().size()];
          String [] schedules_ready=new String[priorityList.getItems().size()];

          //???????????????????????????
        for(int i=0;i<priorityList.getItems().size();i++){
             priorities_ready[i]= priorityList.getItems().get(i);
             runningTimes_ready[i]=runningTimeList.getItems().get(i);
             schedules_ready[i]=scheList.getItems().get(i);
         }
       //???????????????????????????
        int []priorities_sorted=new int[priorityList.getItems().size()];

       for(int j=0;j<priorityList.getItems().size();j++){
            priorities_sorted[j]=Integer.parseInt(priorities_ready[j]);

        }
        int []order =new int[priorityList.getItems().size()]; int orderTemp=0;
        int []temp=Arrays.copyOf(priorities_sorted,priorityList.getItems().size());
        Arrays.sort(priorities_sorted);
        for(int j=0;j<priorities_sorted.length;j++){
            for(int t=0;t<priorities_sorted.length;t++){
                if(temp[t]==priorities_sorted[j]){
                    order[orderTemp]=t;
                    orderTemp++;
                }

            }
        }
         int counter=0;
        //??????cpu????????????????????????????????????
        for(int j=0;priorityList.getItems().size()<cpu?j<priorityList.getItems().size():j<cpu;j++){
            scheList_ready.getItems().add(schedules_ready[order[j]]);
            runningTimeList_ready.getItems().add(runningTimes_ready[order[j]]);
            prioprityList_ready.getItems().add(String.valueOf(priorities_sorted[j]));
            counter++;
        }
        //??????????????????
        for(int j=0;j<counter;j++){
            scheList.getItems().remove(0);
            runningTimeList.getItems().remove(0);
            priorityList.getItems().remove(0);
        }
       //?????????????????????cpu????????????,?????????????????????
     cpuSchedule firstSchedule=new cpuSchedule();
        firstSchedule.schedule=schedules_ready[order[0]];
        firstSchedule.runningTime=Integer.parseInt(runningTimes_ready[order[0]]);
        firstSchedule.priority=Integer.parseInt(priorities_ready[order[0]]);
        scheList_ready.getItems().remove(0);
        runningTimeList_ready.getItems().remove(0);
        prioprityList_ready.getItems().remove(0);
       addTo(firstSchedule.schedule,firstSchedule.runningTime,firstSchedule.priority);

//?????????????????????

    }


    @FXML
    void pauseBtnAction(ActionEvent actionEvent){

        //  ??????????????????????????????
        if(scheList_ready.getSelectionModel().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("???????????????????????????????????????");
            alert.show();
            return;
        }
        //???????????????????????????
        String schedule_paused=scheList_ready.getSelectionModel().getSelectedItem();
        int order=scheList_ready.getSelectionModel().getSelectedIndex();
        String runningTime_paused=runningTimeList_ready.getItems().get(order);
        String priority_paused=prioprityList_ready.getItems().get(order);

        //????????????????????????
        System.out.println("??????????????????"+schedule_paused+order);

        //????????????????????????
        cpuList_hanging.getItems().add(schedule_paused);
        timeList_hanging.getItems().add(runningTime_paused);
        prioprity_hanging.getItems().add(priority_paused);

        //????????????????????????
        scheList_ready.getItems().remove(order);
        runningTimeList_ready.getItems().remove(order);
        prioprityList_ready.getItems().remove(order);


        //        System.out.println("test");

    }

   void scheduleSort(ListView<String> schedules,ListView<String> runningTime,ListView<String> priority){
       int cpu= Integer.parseInt(CPU.getValue());

        String[] priorities_ready=new String[priority.getItems().size()];
       String []runningTimes_ready=new String[schedules.getItems().size()];
       String [] schedules_ready=new String[runningTime.getItems().size()];

       for(int i=0;i<priority.getItems().size();i++){
           priorities_ready[i]= priority.getItems().get(i);
           runningTimes_ready[i]=runningTime.getItems().get(i);
           schedules_ready[i]=schedules.getItems().get(i);
       }
       //???????????????????????????
       int []priorities_sorted=new int[priority.getItems().size()];

       for(int j=0;j<priority.getItems().size();j++){
           priorities_sorted[j]=Integer.parseInt(priorities_ready[j]);

       }
       int []order =new int[priority.getItems().size()]; int orderTemp=0;
       int []temp=Arrays.copyOf(priorities_sorted,priority.getItems().size());
       Arrays.sort(priorities_sorted);
       for(int j=0;j<priorities_sorted.length;j++){
           for(int t=0;t<priorities_sorted.length;t++){
               if(temp[t]==priorities_sorted[j]){
                   order[orderTemp]=t;
                   orderTemp++;
               }

           }
       }
       int tempCount=schedules.getItems().size();
       schedules.getItems().clear();
       runningTime.getItems().clear();
       priority.getItems().clear();

       for(int j=0;tempCount<cpu?j<tempCount:j<cpu;j++){
           schedules.getItems().add(schedules_ready[order[j]]);
           runningTime.getItems().add(runningTimes_ready[order[j]]);
           priority.getItems().add(String.valueOf(priorities_sorted[j]));
       }



   }
    @FXML
    void dePauseBtnAction(ActionEvent event){
        //  ??????????????????????????????
        if(cpuList_hanging.getSelectionModel().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("???????????????????????????????????????");
            alert.show();
            return;
        }
        //???????????????

        //  ??????cpu??????
        int cpu=Integer.parseInt(CPU.getValue());
        //???????????????????????????????????????
        String schedule_de_paused = cpuList_hanging.getSelectionModel().getSelectedItem();
        int order =cpuList_hanging.getSelectionModel().getSelectedIndex();
        String runningTime_de_paused=timeList_hanging.getItems().get(order);
        String priority_de_paused= prioprity_hanging.getItems().get(order);

        //???????????????????????????????????????????????????????????????
        double num=scheList_ready.getItems().size();
        if(num>=cpu){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ERROR???????????????");
            alert.show();
        }else{
            scheList_ready.getItems().add(schedule_de_paused);
            runningTimeList_ready.getItems().add(runningTime_de_paused);
            prioprityList_ready.getItems().add(priority_de_paused);
        }

        //????????????????????????
        cpuList_hanging.getItems().remove(order);
        timeList_hanging.getItems().remove(order);
        prioprity_hanging.getItems().remove(order);

      //?????????????????????????????????

      scheduleSort(scheList_ready,runningTimeList_ready,prioprityList_ready);

//      prioprityList_ready.getItems().sort();

    }

    @FXML
    void nextBtnAction(ActionEvent event) {

        //??????cpu??????????????????????????????
        String cpuSchedule=cpuLabel.getText();
        int cpuSchedule_time=Integer.parseInt(timeLable.getText());
        int cpuSchedule_priority=Integer.parseInt(priorityLable.getText());
        //?????????????????????1????????????cpu??????????????????

        if(cpuSchedule_time==1){
            scheList_end.getItems().add(cpuSchedule);
            scheduleSort(scheList_ready,runningTimeList_ready,prioprityList_ready);
            cpuLabel.setText(scheList_ready.getItems().get(0));
            timeLable.setText(runningTimeList_ready.getItems().get(0));
            priorityLable.setText(prioprityList_ready.getItems().get(0));
        }


        //???????????????1??????????????????-1??????????????????????????????????????????
        if(cpuSchedule_priority==1){
            cpuSchedule_time--;
            scheList_ready.getItems().add(cpuSchedule);
            runningTimeList_ready.getItems().add(String.valueOf(cpuSchedule_time));
            prioprityList_ready.getItems().add(String.valueOf(cpuSchedule_priority));
            scheduleSort(scheList_ready,runningTimeList_ready,prioprityList_ready);
            addTo(scheList_ready.getItems().get(0),Integer.parseInt(runningTimeList_ready.getItems().get(0)),Integer.parseInt(prioprityList_ready.getItems().get(0)));
            prioprityList_ready.getItems().remove(0);
            scheList_ready.getItems().remove(0);
            runningTimeList_ready.getItems().remove(0);
        }
        //?????????1????????????????????????????????????-1??????????????????????????????
        if(cpuSchedule_priority!=1&&cpuSchedule_time!=1){
            cpuSchedule_time--;
            cpuSchedule_priority--;
            //?????????????????????
            scheList_ready.getItems().add(cpuSchedule);
            runningTimeList_ready.getItems().add(String.valueOf(cpuSchedule_time));
            prioprityList_ready.getItems().add(String.valueOf(cpuSchedule_priority));
            //????????????
            scheduleSort(scheList_ready,runningTimeList_ready,prioprityList_ready);
            addTo(scheList_ready.getItems().get(0),Integer.parseInt(runningTimeList_ready.getItems().get(0)),Integer.parseInt(prioprityList_ready.getItems().get(0)));
            //??????????????????
            prioprityList_ready.getItems().remove(0);
            scheList_ready.getItems().remove(0);
            runningTimeList_ready.getItems().remove(0);
        }
        //????????????????????????????????????
        if(cpuSchedule_time==0){
            //?????????????????????????????????????????????????????????????????????
            if(!scheList.getItems().isEmpty()){
                scheList_ready.getItems().add(scheList.getItems().get(0));
                runningTimeList_ready.getItems().add(String.valueOf(runningTimeList.getItems().get(0)));
                prioprityList_ready.getItems().add(String.valueOf(priorityList.getItems().get(0)));
                scheduleSort(scheList_ready,runningTimeList_ready,prioprityList_ready);
                //??????????????????
                scheList.getItems().remove(0);
                priorityList.getItems().remove(0);
                runningTimeList.getItems().remove(0);
            }
            addTo(scheList_ready.getItems().get(0),Integer.parseInt(runningTimeList_ready.getItems().get(0)),Integer.parseInt(prioprityList_ready.getItems().get(0)));
            //??????????????????
            prioprityList_ready.getItems().remove(0);
            scheList_ready.getItems().remove(0);
            runningTimeList_ready.getItems().remove(0);
        }
        if(scheList_ready.getItems().isEmpty()){
            //??????cpu??????
            priorityLable.setText(null);
            timeLable.setText(null);
            cpuLabel.setText(null);
        }


    }

}
