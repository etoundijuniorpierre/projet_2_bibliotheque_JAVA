DROP DATABASE IF EXISTS postgres;
CREATE DATABASE postgres;

\c postgres

drop  table  if  exists  Penalites;
drop  table  if  exists  Emprunts;
drop  table  if  exists  Membres;
drop  table  if  exists  Livres;

CREATE TABLE Livres (
    id SERIAL PRIMARY KEY,
    isbn VARCHAR(13),
    titre VARCHAR(255),
    auteur VARCHAR(255),
    nationaliteauteur VARCHAR(100),
    categorie VARCHAR(100),
    dateParution VARCHAR(10),
    nombreExemplaires INT
);

CREATE TABLE Membres (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    adhesionDate DATE NOT NULL
);

CREATE TABLE Emprunts (
    empruntId SERIAL PRIMARY KEY,
    membreId INT NOT NULL,
    livreId INT NOT NULL,
    dateEmprunt DATE NOT NULL,
    dateRetourPrevue DATE NOT NULL,
    dateRetourEffective DATE,
    FOREIGN KEY (membreId) REFERENCES Membres(id) ON DELETE CASCADE,
    FOREIGN KEY (livreId) REFERENCES Livres(id) ON DELETE CASCADE
);

CREATE TABLE Penalites (
    penaliteId SERIAL PRIMARY KEY,
    empruntId INT NOT NULL,
    montant DECIMAL(10, 2) NOT NULL, -- Montant de la pénalité
    FOREIGN KEY (empruntId) REFERENCES Emprunts(empruntId) ON DELETE CASCADE
);




-- insertion dans la table Livres
INSERT INTO livres (isbn, titre, auteur, nationaliteAuteur, categorie, dateParution, nombreExemplaires)
VALUES
    (9781234567890, 'l"Etranger', 'Albert Camus', 'Française', 'Philosophie', '1942-06-01', 12),
    (9781234567891, '1984', 'George Orwell', 'Anglaise', 'Politique', '1949-06-08', 15),
    (9781234567892, 'To Kill a Mockingbird', 'Harper Lee', 'Américaine', 'Littérature', '1960-07-11', 10),
    (9781234567893, 'Introduction to Algorithms', 'Thomas H. Cormen', 'Américaine', 'Informatique', '2009-07-31', 8),
    (9781234567894, 'One Piece Vol. 1', 'Eiichiro Oda', 'Japonaise', 'Manga', '1997-12-24', 20),
    (9781234567895, 'Le Petit Prince', 'Antoine de Saint-Exupéry', 'Française', 'Littérature', '1943-04-06', 18),
    (9781234567896, 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 'Israélienne', 'Scientifique', '2014-02-10', 9),
    (8978123456789, 'Watchmen', 'Alan Moore', 'Anglaise', 'BD', '1986-09-01', 11),
    (9781234567898, 'Les Misérables', 'Victor Hugo', 'Française', 'Littérature', '1862-01-01', 7),
    (9781234567899, 'Méditations Métaphysiques', 'René Descartes', 'Française', 'Philosophie', '1641-08-28', 6),
    (9781234567800, 'Naruto Vol. 1', 'Masashi Kishimoto', 'Japonaise', 'Manga', '1999-09-21', 22),
    (9781234567801, 'The Art of Computer Programming', 'Donald Knuth', 'Américaine', 'Informatique', '1968-01-01', 5),
    (9781234567802, 'La Nausée', 'Jean-Paul Sartre', 'Française', 'Philosophie', '1938-06-01', 12),
    (9781234567803, 'Python Crash Course', 'Eric Matthes', 'Américaine', 'Informatique', '2015-11-01', 10),
    (9781234567804, 'Dragon Ball Vol. 1', 'Akira Toriyama', 'Japonaise', 'Manga', '1984-12-03', 25),
    (9781234567805, 'War and Peace', 'Leo Tolstoy', 'Russe', 'Littérature', '1869-01-01', 8),
    (9781234567806, 'The Selfish Gene', 'Richard Dawkins', 'Anglaise', 'Scientifique', '1976-11-13', 14),
    (9781234567807, 'Calvin and Hobbes', 'Bill Watterson', 'Américaine', 'BD', '1985-11-18', 13),
    (9781234567808, 'Crime and Punishment', 'Fyodor Dostoevsky', 'Russe', 'Littérature', '1866-01-01', 7),
    (9781234567809, 'Critique of Pure Reason', 'Immanuel Kant', 'Allemande', 'Philosophie', '1781-01-01', 4),
    (9781234567810, 'Bleach Vol. 1', 'Tite Kubo', 'Japonaise', 'Manga', '2001-08-07', 18),
    (9781234567811, 'Clean Code', 'Robert C. Martin', 'Américaine', 'Informatique', '2008-08-01', 9),
    (9781234567812, 'The Catcher in the Rye', 'J.D. Salinger', 'Américaine', 'Littérature', '1951-07-16', 12),
    (9781234567813, 'Gödel, Escher, Bach', 'Douglas Hofstadter', 'Américaine', 'Scientifique', '1979-02-05', 5),
    (9781234567814, 'Asterix the Gaul', 'René Goscinny', 'Française', 'BD', '1961-10-29', 20),
    (9781234567815, 'Pride and Prejudice', 'Jane Austen', 'Anglaise', 'Littérature', '1813-01-28', 11),
    (9781234567816, 'Meditations', 'Marcus Aurelius', 'Romaine', 'Philosophie', '180-01-01', 7),
    (9781234567817, 'Fullmetal Alchemist Vol. 1', 'Hiromu Arakawa', 'Japonaise', 'Manga', '2001-07-12', 16),
    (9781234567818, 'Design Patterns', 'Erich Gamma', 'Allemande', 'Informatique', '1994-10-21', 8),
    (9781234567819, 'Moby Dick', 'Herman Melville', 'Américaine', 'Littérature', '1851-10-18', 10),
    (9781234567820, 'Thinking, Fast and Slow', 'Daniel Kahneman', 'Israélienne', 'Scientifique', '2011-10-25', 14),
    (9781234567821, 'The Adventures of Tintin', 'Hergé', 'Belge', 'BD', '1929-01-10', 25),
    (9781234567822, 'Brave New World', 'Aldous Huxley', 'Anglaise', 'Politique', '1932-08-01', 11),
    (9781234567823, 'Attack on Titan Vol. 1', 'Hajime Isayama', 'Japonaise', 'Manga', '2009-09-09', 18),
    (9781234567824, 'The Myth of Sisyphus', 'Albert Camus', 'Française', 'Philosophie', '1942-01-01', 9),
    (9781234567825, 'Head First Java', 'Kathy Sierra', 'Américaine', 'Informatique', '2005-02-09', 12),
    (9781234567826, 'The Brothers Karamazov', 'Fyodor Dostoevsky', 'Russe', 'Littérature', '1880-01-01', 6),
    (9781234567827, 'A Brief History of Time', 'Stephen Hawking', 'Anglaise', 'Scientifique', '1988-03-01', 15),
    (9781234567828, 'Persepolis', 'Marjane Satrapi', 'Iranienne', 'BD', '2000-11-01', 10),
    (9781234567829, 'Democracy in America', 'Alexis de Tocqueville', 'Française', 'Politique', '1835-01-01', 5),
    (9781234567830, 'Death Note Vol. 1', 'Tsugumi Ohba', 'Japonaise', 'Manga', '2003-12-01', 19),
    (9781234567831, 'You Don’t Know JS', 'Kyle Simpson', 'Américaine', 'Informatique', '2014-12-01', 13),
    (9781234567832, 'Don Quixote', 'Miguel de Cervantes', 'Espagnole', 'Littérature', '1605-01-16', 8),
    (9781234567833, 'The Origin of Species', 'Charles Darwin', 'Anglaise', 'Scientifique', '1859-11-24', 10),
    (9781234567834, 'Calvinism', 'Jean Calvin', 'Française', 'Philosophie', '1536-01-01', 7),
    (9781234567835, 'Akira Vol. 1', 'Katsuhiro Otomo', 'Japonaise', 'Manga', '1982-12-20', 14),
    (9781234567836, 'Artificial Intelligence: A Modern Approach', 'Stuart Russell', 'Américaine', 'Informatique', '1995-01-01', 10),
    (9781234567837, 'Frankenstein', 'Mary Shelley', 'Anglaise', 'Littérature', '1818-01-01', 9),
    (9781234567838, 'On the Origin of Inequality', 'Jean-Jacques Rousseau', 'Française', 'Politique', '1755-01-01', 6),
    (9781234567839, 'The Sandman', 'Neil Gaiman', 'Anglaise', 'BD', '1989-01-01', 12);


-- insertion dans la table Membres
INSERT INTO Membres (nom, prenom, email, telephone, adhesionDate)
VALUES
    ('Bessala', 'Tina', 'tina.bessala@example.com', '237704123456', '2023-07-21'),
    ('Fouda', 'David', 'david.fouda@example.com', '237705234567', '2023-08-22'),
    ('Kouadio', 'Valérie', 'valerie.kouadio@example.com', '237706345678', '2023-09-23'),
    ('Manga', 'Julien', 'julien.manga@example.com', '237707456789', '2023-10-24'),
    ('Zang', 'Rita', 'rita.zang@example.com', '237708567890', '2023-11-25'),
    ('Nkongho', 'Sandro', 'sandro.nkongho@example.com', '237709678901', '2023-12-26'),
    ('Tchinda', 'Hélène', 'helene.tchinda@example.com', '237710789012', '2023-01-27'),
    ('Djoumessi', 'Lucie', 'lucie.djoumessi@example.com', '237711890123', '2023-02-28'),
    ('Koua', 'Josephine', 'josephine.koua@example.com', '237712901234', '2023-03-29'),
    ('Ayissi', 'Catherine', 'catherine.ayissi@example.com', '237713012345', '2023-04-30'),
    ('Mbo', 'Fabrice', 'fabrice.mbo@example.com', '237714123456', '2023-05-01'),
    ('Bebey', 'Nathalie', 'nathalie.bebey@example.com', '237715234567', '2023-06-02'),
    ('Ekane', 'Michel', 'michel.ekane@example.com', '237716345678', '2023-07-03'),
    ('Fombo', 'Blaise', 'blaise.fombo@example.com', '237717456789', '2023-08-04'),
    ('Ewang', 'Isabelle', 'isabelle.ewang@example.com', '237718567890', '2023-09-05'),
    ('Tchouo', 'Thierry', 'thierry.tchouo@example.com', '237719678901', '2023-10-06'),
    ('Moukouri', 'Rachel', 'rachel.moukouri@example.com', '237720789012', '2023-11-07'),
    ('Tchinga', 'Fernand', 'fernand.tchinga@example.com', '237721890123', '2023-12-08'),
    ('Ndoula', 'Marthe', 'marthe.ndoula@example.com', '237722901234', '2023-01-09'),
    ('Bongue', 'Patricia', 'patricia.bongue@example.com', '237723012345', '2023-02-10'),
    ('Mavoungou', 'Guy', 'guy.mavoungou@example.com', '237724123456', '2023-03-11'),
    ('Tanda', 'Grace', 'grace.tanda@example.com', '237725234567', '2023-04-12'),
    ('Noutchoume', 'Sylvain', 'sylvain.noutchoume@example.com', '237726345678', '2023-05-13'),
    ('Ebo', 'Amandine', 'amandine.ebo@example.com', '237727456789', '2023-06-14'),
    ('Mouafo', 'Michel', 'michel.mouafo@example.com', '237728567890', '2023-07-15'),
    ('Bache', 'Gaëtan', 'gaetan.bache@example.com', '237729678901', '2023-08-16'),
    ('Ebelle', 'Aline', 'aline.ebelle@example.com', '237730789012', '2023-09-17'),
    ('Limbong', 'Eric', 'eric.limbong@example.com', '237731890123', '2023-10-18'),
    ('Kouekam', 'Maurice', 'maurice.kouekam@example.com', '237732901234', '2023-11-19'),
    ('Djimadoum', 'Rolande', 'rolande.djimadoum@example.com', '237733012345', '2023-12-20'),
    ('Mbah', 'Didier', 'didier.mbah@example.com', '237734123456', '2023-01-21'),
    ('Tchuem', 'Mireille', 'mireille.tchuem@example.com', '237735234567', '2023-02-22'),
    ('Eyamba', 'Laurent', 'laurent.eyamba@example.com', '237736345678', '2023-03-23'),
    ('Akoa', 'Jessica', 'jessica.akoa@example.com', '237737456789', '2023-04-24'),
    ('Foulen', 'Pauline', 'pauline.foulen@example.com', '237738567890', '2023-05-25'),
    ('Zemmo', 'Blandine', 'blandine.zemmo@example.com', '237739678901', '2023-06-26'),
    ('Essindi', 'Patrice', 'patrice.essindi@example.com', '237740789012', '2023-07-27'),
    ('Tita', 'Valerie', 'valerie.tita@example.com', '237741890123', '2023-08-28'),
    ('Ebiane', 'Hugues', 'hugues.ebiane@example.com', '237742901234', '2023-09-29'),
    ('Bissek', 'Paul', 'paul.bissek@example.com', '237743012345', '2023-10-30'),
    ('Mandjio', 'Frédéric', 'frederic.mandjio@example.com', '237744123456', '2023-11-01'),
    ('Bendi', 'Stella', 'stella.bendi@example.com', '237745234567', '2023-12-02'),
    ('Bebey', 'Joseph', 'joseph.bebey@example.com', '237746345678', '2023-01-03'),
    ('Mbanga', 'Eugène', 'eugene.mbanga@example.com', '237747456789', '2023-02-04'),
    ('Ebongue', 'Alice', 'alice.ebongue@example.com', '237748567890', '2023-03-05'),
    ('Kouoh', 'Mireille', 'mireille.kouoh@example.com', '237749678901', '2023-04-06'),
    ('Njoya', 'Jean', 'jean.njoya@example.com', '237695123456', '2023-05-01'),
    ('Mbang', 'Paul', 'paul.mbang@example.com', '237698234567', '2023-06-02'),
    ('Ngwang', 'Marie', 'marie.ngwang@example.com', '237677345678', '2023-07-03'),
    ('Tchouang', 'Eric', 'eric.tchouang@example.com', '237687456789', '2023-08-04'),
    ('Ndukong', 'Alice', 'alice.ndukong@example.com', '237678567890', '2023-09-05'),
    ('Mokoki', 'Gustave', 'gustave.mokoki@example.com', '237675678901', '2023-10-06'),
    ('Fonkwa', 'Charlotte', 'charlotte.fonkwa@example.com', '237690789012', '2023-11-07'),
    ('Diala', 'Joseph', 'joseph.diala@example.com', '237691890123', '2023-12-08'),
    ('Moly', 'Sophie', 'sophie.moly@example.com', '237692901234', '2023-01-09'),
    ('Atsu', 'Pierre', 'pierre.atsu@example.com', '237693012345', '2023-02-10'),
    ('Njoya', 'Clara', 'clara.njoya@example.com', '237694123456', '2023-03-11'),
    ('Mpondo', 'Luc', 'luc.mpondo@example.com', '237695234567', '2023-04-12'),
    ('Fombo', 'Brigitte', 'brigitte.fombo@example.com', '237696345678', '2023-05-13'),
    ('Talla', 'Benoit', 'benoit.talla@example.com', '237697456789', '2023-06-14'),
    ('Ombolo', 'Julie', 'julie.ombolo@example.com', '237698567890', '2023-07-15'),
    ('Ngah', 'Michel', 'michel.ngah@example.com', '237699678901', '2023-08-16'),
    ('Tanyi', 'Sylvie', 'sylvie.tanyi@example.com', '237700789012', '2023-09-17'),
    ('Bita', 'Kouadio', 'kouadio.bita@example.com', '237701890123', '2023-10-18'),
    ('Adom', 'Samuel', 'samuel.adom@example.com', '237702901234', '2023-11-19'),
    ('Chouo', 'Esther', 'esther.chouo@example.com', '237703012345', '2023-12-20'),
    ('Dupont', 'Michel', 'michel.dupont@example.com', '237701234567', '2023-04-22'),
    ('Lebeuf', 'Sophie', 'sophie.lebeuf@example.com', '237712345678', '2023-05-15'),
    ('Tchoumi', 'Bernadette', 'bernadette.tchoumi@example.com', '237723456789', '2023-06-18');



-- insertion dans la table emprunts
INSERT INTO Emprunts (membreId, livreId, dateEmprunt, dateRetourPrevue, dateRetourEffective) VALUES
                                                                                                 (1, 1, '2024-01-01', '2024-01-15', '2024-01-18'),
                                                                                                 (2, 2, '2024-01-02', '2024-01-16', NULL),
                                                                                                 (3, 3, '2024-01-03', '2024-01-17', '2024-01-25'),
                                                                                                 (4, 4, '2024-01-04', '2024-01-18', '2024-01-18'),
                                                                                                 (5, 5, '2024-01-05', '2024-01-19', NULL),
                                                                                                 (6, 6, '2024-01-06', '2024-01-20', '2024-01-19'),
                                                                                                 (7, 7, '2024-01-07', '2024-01-21', NULL),
                                                                                                 (8, 8, '2024-01-08', '2024-01-22', '2024-01-23'),
                                                                                                 (9, 9, '2024-01-09', '2024-01-23', '2024-01-23'),
                                                                                                 (10, 10, '2024-01-10', '2024-01-24', NULL),
                                                                                                 (11, 11, '2024-01-11', '2024-01-25', '2024-01-30'),
                                                                                                 (12, 12, '2024-01-12', '2024-01-26', NULL),
                                                                                                 (13, 13, '2024-01-13', '2024-01-27', '2024-02-25'),
                                                                                                 (14, 14, '2024-01-14', '2024-01-28', '2024-01-28'),
                                                                                                 (15, 15, '2024-01-15', '2024-01-29', NULL),
                                                                                                 (16, 16, '2024-01-16', '2024-01-30', '2024-02-02'),
                                                                                                 (17, 17, '2024-01-17', '2024-01-31', NULL),
                                                                                                 (18, 18, '2024-01-18', '2024-02-01', '2024-02-03'),
                                                                                                 (19, 19, '2024-01-19', '2024-02-02', '2024-02-02'),
                                                                                                 (20, 20, '2024-01-20', '2024-02-03', NULL),
                                                                                                 (21, 21, '2024-01-21', '2024-02-04', NULL),
                                                                                                 (22, 22, '2024-01-22', '2024-02-05', '2024-02-08'),
                                                                                                 (23, 23, '2024-01-23', '2024-02-06', NULL),
                                                                                                 (24, 24, '2024-01-24', '2024-02-07', NULL),
                                                                                                 (25, 25, '2024-01-25', '2024-02-08', '2024-02-10'),
                                                                                                 (26, 26, '2024-01-26', '2024-02-09', NULL),
                                                                                                 (27, 27, '2024-01-27', '2024-02-10', '2024-02-05'),
                                                                                                 (28, 28, '2024-01-28', '2024-02-11', '2024-02-10'),
                                                                                                 (29, 29, '2024-01-29', '2024-02-12', NULL),
                                                                                                 (30, 30, '2024-01-30', '2024-02-13', NULL),
                                                                                                 (31, 31, '2024-01-31', '2024-02-14', '2024-02-16'),
                                                                                                 (32, 32, '2024-02-01', '2024-02-15', NULL),
                                                                                                 (33, 33, '2024-02-02', '2024-02-16', '2024-02-20'),
                                                                                                 (34, 34, '2024-02-03', '2024-02-17', '2024-02-15'),
                                                                                                 (35, 35, '2024-02-04', '2024-02-18', NULL),
                                                                                                 (36, 36, '2024-02-05', '2024-02-19', NULL),
                                                                                                 (37, 37, '2024-02-06', '2024-02-20', '2024-02-20'),
                                                                                                 (38, 38, '2024-02-07', '2024-02-21', NULL),
                                                                                                 (39, 39, '2024-02-08', '2024-02-22', '2024-02-24'),
                                                                                                 (40, 40, '2024-02-09', '2024-02-23', '2024-02-23'),
                                                                                                 (41, 41, '2024-02-10', '2024-02-24', '2024-03-24'),
                                                                                                 (42, 42, '2024-02-11', '2024-02-25', NULL),
                                                                                                 (43, 43, '2024-02-12', '2024-02-26', NULL),
                                                                                                 (44, 44, '2024-02-13', '2024-02-27', '2024-02-27'),
                                                                                                 (45, 45, '2024-02-14', '2024-02-28', '2024-03-03'),
                                                                                                 (46, 46, '2024-02-15', '2024-02-29', NULL),
                                                                                                 (47, 47, '2024-02-16', '2024-03-01', '2024-03-01'),
                                                                                                 (48, 48, '2024-02-17', '2024-03-02', NULL),
                                                                                                 (49, 49, '2024-02-18', '2024-03-03', '2024-03-03'),
                                                                                                 (50, 50, '2024-02-19', '2024-03-04', '2024-03-05'),
                                                                                                 (1, 1, '2024-02-20', '2024-03-05', NULL),
                                                                                                 (69, 50, '2024-03-01', '2024-03-15', '2024-03-15');


-- Calculer et insérer les pénalités pour les emprunts en retard
INSERT INTO penalites (empruntid, montant) VALUES
(1,300),
(2,800),
(3,100),
(4,500),
(5,2900),
(6,300),
(7,200),
(8,300),
(9,200),
(10,200),
(11,400),
(12,200),
(13,2900),
(14,400),
(15,100);



