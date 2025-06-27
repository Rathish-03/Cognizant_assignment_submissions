SET SERVEROUTPUT ON;

DECLARE
    v_student_name VARCHAR2(100) := 'Alice Smith';
    v_score        NUMBER := 85;
    v_grade        VARCHAR2(1);
    v_message      VARCHAR2(100);
BEGIN
    IF v_score >= 90 THEN
        v_grade := 'A';
        v_message := 'Excellent work!';
    ELSIF v_score >= 80 THEN
        v_grade := 'B';
        v_message := 'Very good!';
    ELSIF v_score >= 70 THEN
        v_grade := 'C';
        v_message := 'Good effort.';
    ELSIF v_score >= 60 THEN
        v_grade := 'D';
        v_message := 'Needs improvement.';
    ELSE
        v_grade := 'F';
        v_message := 'Please see your instructor.';
    END IF;

    DBMS_OUTPUT.PUT_LINE('--- Grade Determination (IF-ELSIF-ELSE) ---');
    DBMS_OUTPUT.PUT_LINE('Student: ' || v_student_name);
    DBMS_OUTPUT.PUT_LINE('Score: ' || v_score);
    DBMS_OUTPUT.PUT_LINE('Grade: ' || v_grade);
    DBMS_OUTPUT.PUT_LINE('Message: ' || v_message);
    DBMS_OUTPUT.PUT_LINE('------------------------------------------');

    CASE v_grade
        WHEN 'A' THEN
            DBMS_OUTPUT.PUT_LINE('Special Recognition: Keep up the outstanding performance!');
        WHEN 'F' THEN
            DBMS_OUTPUT.PUT_LINE('Important: Academic counseling is recommended.');
        ELSE
            NULL;
    END CASE;

    DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- Demonstrating a WHILE LOOP ---');
    DECLARE
        v_countdown NUMBER := 3;
    BEGIN
        WHILE v_countdown > 0 LOOP
            DBMS_OUTPUT.PUT_LINE('Countdown: ' || v_countdown);
            v_countdown := v_countdown - 1;
        END LOOP;
        DBMS_OUTPUT.PUT_LINE('Liftoff!');
    END;

    DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- Demonstrating a FOR LOOP (Multiplication Table for 7) ---');
    FOR i IN 1..5 LOOP
        DBMS_OUTPUT.PUT_LINE('7 x ' || i || ' = ' || (7 * i));
    END LOOP;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
END;
/