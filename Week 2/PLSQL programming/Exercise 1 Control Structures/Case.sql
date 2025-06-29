DECLARE
    v_member_name VARCHAR2(100) := 'Nami';
    v_role        VARCHAR2(50);
    v_description VARCHAR2(200);
BEGIN
    SELECT role
    INTO v_role
    FROM PirateMembers
    WHERE member_name = v_member_name;

    CASE v_role
        WHEN 'Captain' THEN
            v_description := 'The fearless leader of the crew.';
        WHEN 'Swordsman' THEN
            v_description := 'A master of blades, often the combat specialist.';
        WHEN 'Navigator' THEN
            v_description := 'Guides the ship through treacherous seas.';
        WHEN 'Sniper' THEN
            v_description := 'A long-range combatant with sharp aim.';
        WHEN 'First Commander' THEN
            v_description := 'The top subordinate to the Captain in a large crew.';
        ELSE
            v_description := 'An essential crew member with a unique role.';
    END CASE;

    DBMS_OUTPUT.PUT_LINE('--- Member Role Description ---');
    DBMS_OUTPUT.PUT_LINE('Member: ' || v_member_name);
    DBMS_OUTPUT.PUT_LINE('Role: ' || v_role);
    DBMS_OUTPUT.PUT_LINE('Description: ' || v_description);

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Member ' || v_member_name || ' not found.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An unexpected error occurred: ' || SQLERRM);
END;
/


DECLARE
    v_member_name VARCHAR2(100) := 'Marco';
    v_role        VARCHAR2(50);
    v_description VARCHAR2(200);
BEGIN
    SELECT role
    INTO v_role
    FROM PirateMembers
    WHERE member_name = v_member_name;

    CASE v_role
        WHEN 'Captain' THEN
            v_description := 'The fearless leader of the crew.';
        WHEN 'Swordsman' THEN
            v_description := 'A master of blades, often the combat specialist.';
        WHEN 'Navigator' THEN
            v_description := 'Guides the ship through treacherous seas.';
        WHEN 'Sniper' THEN
            v_description := 'A long-range combatant with sharp aim.';
        WHEN 'First Commander' THEN
            v_description := 'The top subordinate to the Captain in a large crew.';
        ELSE
            v_description := 'An essential crew member with a unique role.';
    END CASE;

    DBMS_OUTPUT.PUT_LINE('--- Member Role Description ---');
    DBMS_OUTPUT.PUT_LINE('Member: ' || v_member_name);
    DBMS_OUTPUT.PUT_LINE('Role: ' || v_role);
    DBMS_OUTPUT.PUT_LINE('Description: ' || v_description);

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Member ' || v_member_name || ' not found.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An unexpected error occurred: ' || SQLERRM);
END;
/