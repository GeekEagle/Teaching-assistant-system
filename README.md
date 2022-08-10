# Teaching-assistant-system
Software Engineering Design
The teaching assistant system is an assistant platform system for teachers and students. It's designed to provide a platform for collecting extracurricular tasks and improve teaching efficiency. 

It needs to realize the functions of user login, authority management, real-time information input and output, equipment control, teacher and student information processing, course and score information query, homework submission and question answering status update. The development of database, the definition of each interface, the design and planning of each interface after subdivision and the coding. The debugging of the upper part and the testing of the whole system, as well as the preparation of various documents that need to be delivered and do not need to be delivered in the whole software life cycle.

The whole functions of the system are followed:

① login function: the customer needs to input the account password and log in when using the teaching assistance system. After login, enter the corresponding interface according to the identity to ensure the security of information.

② Bluetooth sign in function: teachers can publish sign in, and students can use Bluetooth devices. If they are recognized by the system, they will be deemed as successful and displayed on the sign in interface.

③ teachers enter scores: in the teacher login interface, teachers enter students' student number, course number, scores and credits, and then click enter scores to insert a record into the score information table.

④ teacher Release Notice: in the teacher login interface, the teacher releases the notice, selects the type of notice, whether it is "assignment" or "notice", and inserts the course information table. The initialization status is unread.

⑤ teaching assistant question answering: each time the teaching assistant clicks the "teaching assistant question answering" button, each time a student's question appears, the teaching assistant gives an answer in the box below, and clicks "upload question answering" to change the status of this question to "solved" in the information table to be answered.

⑥ teaching assistant assignment scoring: the teaching assistant can click "next assignment" to see a student's assignment submission record, then input data in the score box, and then click assignment scoring to update the student's assignment score in the assignment submission form. If

⑦ students submit homework: when students click "do homework", a homework left by the teacher will appear in the homework submission box, and it is the type that shows unread. When students click "submit homework", the homework record will be submitted to the homework submission form, and the teaching assistant can give points.

⑧ student viewing notice: students can click the "view message" button to view the first unread message. After viewing, the message in the course information table will be changed to read.

⑨ student questions: in the student interface, enter questions in the question answering box, and then click upload to answer questions.

⑩ students view and answer questions: students can click the "query and answer" button to see the questions and answer records they have asked.
