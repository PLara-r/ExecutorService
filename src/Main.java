import java.util.concurrent.*;
public class Main {
    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("begin");
            service.execute(() -> System.out.println("Printing zoo inventory"));
            service.execute(() -> {for(int i=0; i<3; i++)
                System.out.println("Printing record: "+i);}
            );
            service.execute(() -> System.out.println("Printing zoo inventory"));
            System.out.println("end");
        } finally {
            if(service != null) service.shutdown();
        }
    }}
    //begin
//end
//Printing zoo inventory
//Printing record: 0
//Printing record: 1
//Printing record: 2
//Printing zoo inventory


//В этом примере мы использовали newSingleThreadExecutor()метод, который является самым простым,
// ExecutorServiceкоторый мы могли создать. В отличие от нашего предыдущего примера,
// в котором у нас было три дополнительных потока для вновь созданных задач, в этом примере используется только один,
// что означает, что потоки упорядочат свои результаты. Например, следующее является возможным выводом
// для этого фрагмента кода:
//
//Begin
//Printing zoo inventory
//Printing record: 0
//Printing record: 1
//End
//Printing record: 2
//Printing zoo inventory

//При использовании однопотокового исполнителя результаты гарантированно будут выполнены в том порядке,
// в котором они добавлены в службу исполнителя. Обратите внимание, что endтекст выводится,
// когда наши задачи исполнения потоков все еще выполняются.
// Это связано с тем, что main()метод все еще является независимым потоком от ExecutorServiceи может выполнять
// задачи во время работы другого потока.

//Выполнение нескольких задач

//В предыдущем примере вполне возможно, что все три задачи были отправлены на выполнение еще до того,
// как была запущена первая задача. В этом случае однопотоковый исполнитель поставит задачи в очередь
// и дождется завершения предыдущей задачи перед выполнением следующей задачи.
// Хотя задачи гарантированно выполняются в том порядке, в котором они представлены для однопотокового исполнителя,
// вы избегаете полагаться на это поведение для упорядочения событий. Как вы увидите позже в этой главе,
// когда мы увеличиваем количество потоков в службе executor, эта гарантия исчезает.



