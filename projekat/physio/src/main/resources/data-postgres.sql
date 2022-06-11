insert into patient
(lbo, name, surname)
values(
    'lbo1', 'Imenko', 'Prezimenic'
);

insert into pain
(pain_localization, pain_type, simple_pain_intensity)
values(
    'CERVICAL', 'MECHANICAL', 'MEDIUM'
);

insert into pain
(pain_localization, pain_type, simple_pain_intensity)
values(
    'LUMBAL', 'MECHANICAL', 'MILD'
);

insert into diagnosis
(diagnosis_date, illness, pain_intensity, pain_id)
values (
        '2022-06-11', 'PRIMARY_CERVICAL_SYNDROME', 'WORSENING', 1
);      

insert into diagnosis
(diagnosis_date, illness, pain_intensity, pain_id)
values (
        '2022-06-11', 'LUMBAGO', 'MILD', 2
);   

insert into patient_diagnoses
(patient_lbo, diagnosis_id)
values (
    'lbo1', 1
);

insert into patient_diagnoses
(patient_lbo, diagnosis_id)
values (
    'lbo1', 2
);

insert into treatment
(patient_lbo, diagnosis_id)
values(
    'lbo1', 1
);

insert into treatment
(patient_lbo, diagnosis_id)
values(
    'lbo1', 2
);

insert into therapy
(locality, sessions, therapy_type)
values (
        'vrat', 20, 'ELECTRO_PHORESYS_NOVOCAINE'
);

insert into therapy
(locality, sessions, therapy_type)
values(
        'donja kicma', 10, 'CHIROPRACTIC_ADJUSTMENT'
);

insert into treatment_therapies
(treatment_id, therapy_id)
values(
    1, 1
);

insert into treatment_therapies
(treatment_id, therapy_id)
values(
    2, 2
);