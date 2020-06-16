<?php

namespace BaskelBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;


/**
 * Produit
 *
 * @ORM\Table(name="produit")
 * @ORM\Entity(repositoryClass="BaskelBundle\Repository\ProduitRepository")
 * @UniqueEntity(
 *     fields={"reference"},
 *     errorPath="reference",
 *     message="This reference is already in use."
 * )
 */
class Produit
{
    /**
     * @var int
     *
     *@ORM\Column(name="id_produit", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     * @Assert\NotBlank
     * @ORM\Column(name="nom", type="string", length=10)
     * @Assert\Length(
     *      min = 1,
     *      max = 1000,
     *      minMessage = "the name must be at least {{ limit }} characters long",
     *      maxMessage = "the name cannot be longer than {{ limit }} characters"
     *     )
     */
    private $nom;

    /**
     * @var string
     * @Assert\NotBlank
     * @ORM\Column(name="reference", type="string", length=7)
     *      * @Assert\Length(
     *      min = 1,
     *      max = 7,
     *      minMessage = "the reference must be  {{ limit }} characters long",
     *      maxMessage = "the reference must be  {{ limit }} characters long"
     *     )
     *
     */
    private $reference;

    /**
     *
     * @Assert\NotBlank
     * @ORM\ManyToOne(targetEntity="\BaskelBundle\Entity\CategorieP")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_categorie", referencedColumnName="id_cp")
     * })
     */
    private $idCategorie;

    /**
     * @var int
     *
     * @ORM\Column(name="quantite", type="integer")
     * @Assert\NotBlank
     * @Assert\Range(
     *      min = 1,
     *      max =1000,
     *      minMessage = "You must be at least {{ limit }} to enter",
     *      maxMessage = "You cannot be more than {{ limit }} to enter"
     * )
     */
    private $quantite;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float")
     * @Assert\NotBlank
     * @Assert\Range(min=20)
     */
    private $prix;

    /**
     * @var string
     *
     * @Assert\Image();
     * @Assert\NotBlank
     * @ORM\Column(name="image", type="string", length=1000)
     *
     *
     *
     */
    private $image;

    /**
     * @var string
     * @Assert\NotBlank
     * @ORM\Column(name="description", type="string", length=255)
     */
    private $description;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return Produit
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Set reference
     *
     * @param string $reference
     *
     * @return Produit
     */
    public function setReference($reference)
    {
        $this->reference = $reference;

        return $this;
    }

    /**
     * Get reference
     *
     * @return string
     */
    public function getReference()
    {
        return $this->reference;
    }

    /**
     * Set idCategorie
     *
     * @param integer $idCategorie
     *
     * @return Produit
     */
    public function setIdCategorie($idCategorie)
    {
        $this->idCategorie = $idCategorie;

        return $this;
    }

    /**
     * Get idCategorie
     *
     * @return int
     */
    public function getIdCategorie()
    {
        return $this->idCategorie;
    }

    /**
     * Set quantite
     *
     * @param integer $quantite
     *
     * @return Produit
     */
    public function setQuantite($quantite)
    {
        $this->quantite = $quantite;

        return $this;
    }

    /**
     * Get quantite
     *
     * @return int
     */
    public function getQuantite()
    {
        return $this->quantite;
    }

    /**
     * Set prix
     *
     * @param float $prix
     *
     * @return Produit
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;

        return $this;
    }

    /**
     * Get prix
     *
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * Set image
     *
     * @param string $image
     *
     * @return Produit
     */
    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    /**
     * Get image
     *
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * Set description
     *
     * @param string $description
     *
     * @return Produit
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $this;
    }

    /**
     * Get description
     *
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

/*
    /**
     * @Assert\File(maxSize="500000000k")
     *//*
    public  $file;

    public function getWebpath(){


        return null === $this->image ? null : $this->getUploadDir.'/'.$this->image;
    }
    protected  function  getUploadRootDir(){

        return __DIR__.'/../../../web/images'.$this->getUploadDir();
    }
    protected function getUploadDir(){

        return'';
    }
    public function getUploadFile(){
        if (null === $this->getFile()) {
            $this->image = "logo.png";
            return;
        }


        $this->getFile()->move(
            $this->getUploadRootDir(),
            $this->getFile()->getClientOriginalName()

        );

        // set the path property to the filename where you've saved the file
        $this->image = $this->getFile()->getClientOriginalName();

        // clean up the file property as you won't need it anymore
        $this->file = null;
    }
    /**
     * Sets file.
     *
     * @param UploadedFile $file
     *//*
    public function setFile(UploadedFile $file)
    {
        $this->file = $file;
    }

    /**
     * Get file.
     *
     * @return UploadedFile
     *//*
    public function getFile()
    {
        return $this->file;
    }*/

}

