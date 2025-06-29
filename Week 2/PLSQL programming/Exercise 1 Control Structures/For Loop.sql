DECLARE
    v_crew_id NUMBER := 1; 
    v_crew_name VARCHAR2(100);
    CURSOR c_crew_members IS
        SELECT member_name, role, status
        FROM PirateMembers
        WHERE crew_id = v_crew_id
        ORDER BY individual_bounty DESC;
BEGIN
    SELECT crew_name INTO v_crew_name FROM PirateCrews WHERE crew_id = v_crew_id;

    DBMS_OUTPUT.PUT_LINE('--- Members of ' || v_crew_name || ' ---');

    FOR member_rec IN c_crew_members LOOP
        DBMS_OUTPUT.PUT_LINE('Name: ' || member_rec.member_name || ', Role: ' || member_rec.role || ', Status: ' || member_rec.status);

        IF member_rec.role = 'Captain' THEN
            DBMS_OUTPUT.PUT_LINE('  (This is the Captain!)');
        END IF;
    END LOOP;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Crew ID ' || v_crew_id || ' not found.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An unexpected error occurred: ' || SQLERRM);
END;
/